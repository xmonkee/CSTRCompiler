package CSTRParser

import AST._
import Lexemes._
import scalaz._
import Scalaz._

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

  case class SymbolTable (table: Map[Lexemes.Ident, Type], parent: Option[SymbolTable], fname: Option[Ident] = None)
  {self =>
    def addSymbol(i: Ident, t: Type) = copy(table = table + (i -> t)) // Add to current Level
    def addLevel = SymbolTable(self, fname)
    def removeLevel = parent.get
    def addFName(name: Ident): SymbolTable = copy(fname = Some(name))
    def getCurrentLevel(i: Ident): Option[Type] = table.get(i) // get only from current Level
    def get(i: Ident): Option[Type] =
      table.get(i) match { case None => parent flatMap(_.get(i)) case x => x}
    def containsCurrentLevel(i: Ident) = getCurrentLevel(i) match {case None => false case _ => true}
    def contains(i: Ident) = get(i) match {case None => false case _ => true}

    def typeof(exp: Expression): Option[Type] = {
      def typeofexp(first: Expression, rest: Seq[(_, Expression)]): Option[Type] =
        rest.foldLeft(typeof(first))
        {case (t1, (_, e2)) => (t1, typeof(e2)) match {
          case (Some(Integer), Some(Integer)) => Some(Integer)
          case _ => None
        }}
      exp match{
        case Ident(_) => get(exp.asInstanceOf[Ident])
        case Shiftive(first, rest) => typeofexp(first, rest)
        case Additive(first, rest) => typeofexp(first, rest)
        case Multiplicative(first, rest) => typeofexp(first, rest)
        case Minus(e) => typeof(e) match {case Some(Integer) => Some(Integer) case _ => None}
        case FunctionApplication(name, _) => get(name) flatMap
          {case Func(_, retType) => Some(retType) case _ => None}
        case IntegerConstant(_) => Some(Integer)
        case StringConstant(_) => Some(String)
      }
    }
  }

  object SymbolTable { //constructors
    def apply(): SymbolTable = SymbolTable(Map.empty[Ident, Type], None, None)
    def apply(parent: SymbolTable, fname: Option[Ident]): SymbolTable =
      SymbolTable(Map.empty[Ident, Type], Some(parent), fname)
  }

  object Errors {
    def alreadyExists(i: Ident) = List(s"Symbol ${i.s} already exists")
    def wrongType[A](a: AST, t1: A, t2: A) = List(s"$a: Type Mismatch: expected $t1, got $t2")
    def notFound(i: Ident) = List(s"Symbold ${i.s} not found")
    def couldNotInfer(e: AST) = List(s"Cannot find type of $e")
    def wrongNumArgs(a: AST) = List(s"$a: Wrong Number of arguments passed")
  }

  type State = Writer[List[String], SymbolTable]
  object State {
    def apply(w: List[String], a: SymbolTable): State = a.set(w)
    def apply(a: SymbolTable): State = Writer(List.empty, a)
    def apply(): State = Writer(List.empty, SymbolTable())
  }

  def checkType[A](a: AST, received: Option[A], expected: A)(s: SymbolTable): State =
    received match {
      case None => State(Errors.couldNotInfer(a), s)
      case Some(t) if (t == expected) => State(s)
      case Some(t) => State(Errors.wrongType(a, t, expected), s)
    }

  def checkType[A](a: AST, received: Option[A], expected: Option[A])(s: SymbolTable): State =
    expected match {
      case None => State(Errors.couldNotInfer(a), s)
      case Some(t) => checkType(a, received, t)(s)
    }

  def addSymbol(i: Ident, t: Type)(s: SymbolTable): State = {
    if (s containsCurrentLevel i)
      State(Errors.alreadyExists(i), s)
    else s.addSymbol(i, t).set(Nil)
  }

  def addDeclarator(t: BaseType, d: Declarator)(s: SymbolTable): State = d match {
    case FunctionDeclarator(name, params) =>
      s.addSymbol(name, Func(params, t)).set(Nil)
    case Ident(_) => s.addSymbol(d.asInstanceOf[Ident], t).set(Nil)
  }

  def addParams(params: Seq[ParameterDeclaration])(s: SymbolTable): State = {
    val new_s = params.foldLeft(s) { case (s, ParameterDeclaration(t, name)) => s.addSymbol(name, t) }
    State(new_s)
  }


    def typeCheck(a: Option[AST])(s: SymbolTable): State = if (! a.isDefined) State(s) else typeCheck(a.get)(s)

    def typeCheck(a: AST)(s: SymbolTable): State = a match {

      case Program(children: Seq[TopLevel]) =>
        children.foldLeft(State(s))(_ flatMap typeCheck(_))

      case ExternDeclaration(t, declList: Seq[Declarator]) =>
        declList.foldLeft(State(s))(_ flatMap addDeclarator(t, _))

      case Declaration(t, decList) =>
        decList.foldLeft(State(s))(_ flatMap addDeclarator(t, _))

      case FunctionDefinition(retType, functionDeclarator, body) =>
        val FunctionDeclarator(name, params) = functionDeclarator
        (
          if (s contains name) // typecheck added function declaration
            checkType(name, s.typeof(name), Func(params, retType))(s)
          else // Just add new Declaration
            addDeclarator(retType, functionDeclarator)(s)
        ) map
          (_.addLevel) map //Attach new scope
          (_.addFName(functionDeclarator.name)) flatMap // Add function name to check return type
          addParams(functionDeclarator.params) flatMap // Add function parameters to scope
          typeCheck(body) map // Type check body
          (_.removeLevel)

      case CompoundInstruction(decList, instrList) =>
        (decList ++ instrList).foldLeft(State(s))(_ flatMap typeCheck(_))

      case Assignment(i, e) => checkType(a, s.typeof(i), s.typeof(e))(s)

      case Condition(left, _, right) =>
        checkType(left, s.typeof(left), Integer)(s) flatMap checkType(right, s.typeof(right), Integer)

      case SelectInstruction(cond, then, _else) =>
        typeCheck(cond)(s) map (_.addLevel) flatMap typeCheck(then) map (_.removeLevel) map
          (_.addLevel) flatMap typeCheck(_else) map (_.removeLevel)

      case While(cond, instr ) =>
        typeCheck(cond)(s) map (_.addLevel) flatMap typeCheck(instr) map (_.removeLevel)

      case Do(cond, instr) =>
        typeCheck(cond)(s) map (_.addLevel) flatMap typeCheck(instr) map (_.removeLevel)

      case For(init, cond, fin, instr) =>
        typeCheck(init)(s) flatMap typeCheck(cond) flatMap typeCheck(fin) map
          (_.addLevel) flatMap typeCheck(instr) map (_.removeLevel)

      case Return(exp) =>
        checkType(a, s.fname flatMap s.typeof map (_.asInstanceOf[Func].ret), s.typeof(exp))(s) flatMap typeCheck(exp)

      //For Expressions, we check the constituent parts to be Integer
      case Shiftive(first, rest) => rest.foldLeft(checkType(first, s.typeof(first), Integer)(s))({
        case (st, (_, exp)) => st flatMap checkType(exp, s.typeof(exp), Integer) })
      case Additive(first, rest) => rest.foldLeft(checkType(first, s.typeof(first), Integer)(s))({
        case (st, (_, exp)) => st flatMap checkType(exp, s.typeof(exp), Integer) })
      case Multiplicative(first, rest) => rest.foldLeft(checkType(first, s.typeof(first), Integer)(s))({
        case (st, (_, exp)) => st flatMap checkType(exp, s.typeof(exp), Integer) })
      case Minus(exp) => checkType(exp, s.typeof(exp), Integer)(s)

      case FunctionApplication(name, args) => {
        val first = checkType(name, s get name, s get name)(s) // Check presence of function name
        val params = s get name map (_.asInstanceOf[Func].args)
        params match {
          case Some(ps) if (args.length == ps.length) =>
            (ps zip args).foldLeft(first) {
                case (acc: State, (expected, received)) =>
                  acc flatMap checkType(received, s.typeof(received), expected)
              }
          case Some(ps) => first flatMap (State(Errors.wrongNumArgs(a), _))
          case None => first flatMap (State(Errors.notFound(name), _))
        }
      }

      case _ => State(s)
    }
}
