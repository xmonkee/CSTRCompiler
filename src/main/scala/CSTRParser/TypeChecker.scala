package CSTRParser

import AST._
import Lexemes._

/**
 * Created by mayankmandava on 10/30/15.
 */
object TypeChecker {

  trait Type

  trait BaseType extends Type
  case object Integer extends BaseType
  case object String extends BaseType
  case class Func(args: Seq[BaseType], ret: BaseType) extends Type

  implicit def LexTypeToType(t: Lexemes.Type): BaseType = t match {
    case Lexemes.IntegerDeclaration => Integer
    case Lexemes.StringDeclaration => String
  }

  implicit def ParamListToTypeList(xs: Seq[ParameterDeclaration]): Seq[BaseType] = {
    for {ParameterDeclaration(t, n) <- xs} yield LexTypeToType(t)
    }

  case class SymbolTable (table: Map[Lexemes.Ident, Type], parent: Option[SymbolTable], fname: Option[Ident] = None) {self =>
    def addSymbol(i: Ident, t: Type) = copy(table = table + (i -> t)) // Add to current Level
    def addLevel = SymbolTable(Map.empty[Ident, Type], Some[SymbolTable](self))
    def addFName(name: Ident): SymbolTable = copy(fname = Some(name))
    def removeFName: SymbolTable = copy(fname = None)
    def addLevel(table: Map[Lexemes.Ident, Type]) = SymbolTable(table, Some[SymbolTable](self))
    def getCurrentLevel(i: Ident): Option[Type] = table.get(i) // get only from current Level
    def get(i: Ident): Option[Type] = table.get(i) match { // Get from all the way up if possible
      case None => parent match {
        case None => None
        case Some(st: SymbolTable) => st.get(i)
      }
      case Some(t) => Some(t)
    }
    def containsCurrentLevel(i: Ident) = getCurrentLevel(i) match {case None => false case _ => true}
    def contains(i: Ident) = get(i) match {case None => false case _ => true}
  }

  object Errors {
    def alreadyExists(i: Ident) = s"Symbol ${i.s} already exists"
    def wrongType[A](a: AST, t1: A, t2: A) = s"$a: Type Mismatch: expected $t1, got $t2"
    def notFound(i: Ident) = s"Symbold ${i.s} not found"
    def couldNotInfer(e: AST) = s"Cannot find type of $e"
  }

  case class State(symbolTable: SymbolTable, errors: List[String]) {self =>
    def pass = self
    def addLevel: State = copy(symbolTable = symbolTable.addLevel)
    def addFName(name: Ident): State = copy(symbolTable = symbolTable.addFName(name))
    def removeLevel: State = copy(symbolTable = symbolTable.parent.get)
    def addError(s: String) = copy(errors = s :: errors)

    def addCheck[A](a: AST, received: Option[A], expected: A): State =
    received match {
      case None => addError(Errors.couldNotInfer(a))
      case Some(t) => if (t == expected) self else addError(Errors.wrongType(a, t, expected))
    }

    def addCheck[A](a: AST, received: Option[A], expected: Option[A]): State =
    expected match {
      case None => addError(Errors.couldNotInfer(a))
      case Some(t) => addCheck(a, received, t)
    }

    def addSymbol(i: Ident, t: Type): State = {
      if (symbolTable.containsCurrentLevel(i))
        addError(Errors.alreadyExists(i))
      else
        copy(symbolTable = symbolTable.addSymbol(i, t))
    }

    def addDeclarator(t: BaseType, d: Declarator): State = d match {
      case FunctionDeclarator(name, params) =>
        addSymbol(name, Func(params, t))
      case Ident(s) => addSymbol(d.asInstanceOf[Ident], t)
    }

    def addParams(params: Seq[ParameterDeclaration]): State = {
      copy(symbolTable =
        params.foldLeft(symbolTable) {case (s, ParameterDeclaration(t, name)) => s.addSymbol(name, t)}
      )
    }

    def typeof(exp: Expression): Option[Type] = exp match{
      case Ident(_) => symbolTable.get(exp.asInstanceOf[Ident])
      case Shiftive(first, rest) => Some(Integer)
      case Additive(first, rest) => Some(Integer)
      case Multiplicative(first, rest) => Some(Integer)
      case Minus(_) => Some(Integer)
      case FunctionApplication(name, _) => symbolTable.get(name) flatMap
        {case Func(_, retType) => Some(retType) case _ => None}
      case IntegerConstant(_) => Some(Integer)
      case StringConstant(_) => Some(String)
    }

    def typeCheck(a: Option[AST]): State = if (a.isDefined) pass else typeCheck(a.get)
    def typeCheck(a: AST): State = {
      a match {
        case Program(children: Seq[TopLevel]) => children.foldLeft(self)(_.typeCheck(_))
        case ExternDeclaration(t, declList: Seq[Declarator]) =>
          declList.foldLeft(self)(_.addDeclarator(t, _))
        case Declaration(t, decList) =>
          decList.foldLeft(self)(_.addDeclarator(t, _))
        case FunctionDefinition(retType, functionDeclarator, body) =>
            addCheck(functionDeclarator.name, typeof(functionDeclarator.name), Func(functionDeclarator.params, retType))
            .addLevel
            .addDeclarator(retType, functionDeclarator)
            .addParams(functionDeclarator.params)
            .addFName(functionDeclarator.name)
            .typeCheck(body)
            .removeLevel
        case CompoundInstruction(decList, instrList) => (decList ++ instrList).foldLeft(self)(_.typeCheck(_))
        case Assignment(i, e) => addCheck(a, typeof(i), typeof(e))
        case Condition(left, _, right) =>
          addCheck(left, typeof(left), Integer).addCheck(right, typeof(right), Integer)
        case SelectInstruction(cond, then, _else) =>
          typeCheck(cond).addLevel.typeCheck(then).removeLevel.addLevel.typeCheck(_else)
        case While(cond, instr ) => typeCheck(cond).addLevel.typeCheck(instr).removeLevel
        case Do(cond, instr) => typeCheck(cond).addLevel.typeCheck(instr).removeLevel
        case For(init, cond, fin, instr) => typeCheck(init).typeCheck(cond).typeCheck(fin).addLevel.typeCheck(instr).removeLevel
        case Return(exp) => addCheck(exp, symbolTable.fname flatMap (typeof _) map (_.asInstanceOf[Func].ret), typeof(exp))
        case Shiftive(first, rest) => rest.foldLeft(addCheck(first, typeof(first), Integer))({
          case (s, (_, exp)) => s.addCheck(exp, typeof(exp), Integer) })
        case Additive(first, rest) => rest.foldLeft(addCheck(first, typeof(first), Integer))({
          case (s, (_, exp)) => s.addCheck(exp, typeof(exp), Integer) })
        case Multiplicative(first, rest) => rest.foldLeft(addCheck(first, typeof(first), Integer))({
          case (s, (_, exp)) => s.addCheck(exp, typeof(exp), Integer) })
        case Minus(exp) => addCheck(exp, typeof(exp), Integer)
        case FunctionApplication(name, args) =>
          addCheck(
            name,
            symbolTable.get(name) map (_.asInstanceOf[Func].args),
            args map (typeof _) filter (_.isDefined) map (_.get))
        case _ => pass
      }
    }
  }

  object State {
    def create = State(SymbolTable(Map.empty[Ident, Type], None), List.empty[String])
    def typeCheck(a: AST): State = State.create.typeCheck(a)
  }
}

