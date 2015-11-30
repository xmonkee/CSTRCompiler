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

  case class SymbolTable (
                           table: Map[Lexemes.Ident, Type],
                           parent: Option[SymbolTable],
                           fname: Option[Ident] = None) {self =>

    def addSymbol(i: Ident, t: Type) = copy(table = table + (i -> t)) // Add to current Level
    def addLevel = SymbolTable(self, fname)
    def removeLevel = parent.get
    def addFName(name: Ident): SymbolTable = copy(fname = Some(name)) // Current function name
    def getCurrentLevel(i: Ident): Option[Type] = table.get(i) // get only from current Level
    def get(i: Ident): Option[Type] = // get recursively from current or parent
      table.get(i) match { case None => parent flatMap(_.get(i)) case x => x}
    def containsCurrentLevel(i: Ident) =
      getCurrentLevel(i) match {case None => false case _ => true}
    def contains(i: Ident) =
      get(i) match {case None => false case _ => true}

    def typeof(exp: Expression): Option[Type] = {
      def typeofexpAdditive(first: Expression, rest: Seq[(_, Expression)]): Option[Type] =
        rest.foldLeft(typeof(first))
        {case (t1, (_, e2)) => (t1, typeof(e2)) match {
          case (Some(Integer), Some(Integer)) => Some(Integer)
          case (Some(String), Some(String)) => Some(String)
          case _ => None
        }}
      def typeofexp(first: Expression, rest: Seq[(_, Expression)]): Option[Type] =
        rest.foldLeft(typeof(first))
        {case (t1, (_, e2)) => (t1, typeof(e2)) match {
          case (Some(Integer), Some(Integer)) => Some(Integer)
          case _ => None
        }}
      exp match{
        case Ident(_) => get(exp.asInstanceOf[Ident])
        case Shiftive(first, rest) => typeofexp(first, rest)
        case Additive(first, rest) => typeofexpAdditive(first, rest)
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
    def wrongType[A](a: AST, t1: A, t2: A) = List(s"$a: Type Mismatch: expected $t2, got $t1")
    def notFound(i: Ident) = List(s"Symbold ${i.s} not found")
    def couldNotInfer(e: AST) = List(s"Cannot find type of $e")
    def wrongNumArgs(a: AST) = List(s"$a: Wrong Number of arguments passed")
  }

  type State = Writer[List[String], SymbolTable]
  object State {
    def apply(w: List[String], a: SymbolTable): State = a.set(w)
    def apply(a: SymbolTable): State = State(List.empty, a)
    def apply(): State = State(SymbolTable())
    def fold[A](seq: Seq[A], init: State, f: A => SymbolTable => State) =
      seq.foldLeft(init) {case (st, a) =>
        st flatMap f(a)
      }
    def foldExp(first: Expression, rest: Seq[(_, Expression)], expectedType: Option[Type] = Some(Integer))
               (s: SymbolTable) =
      fold(
        first +: (rest map (_._2)),
        State(s),
        (e: Expression) => checkType(e, s.typeof(e), expectedType)
      )
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
    val new_s = params.foldLeft(s) { case (s, ParameterDeclaration(t, name)) =>
      s.addSymbol(name, t) }
    State(new_s)
  }


  def typeCheck(a: Option[AST])(s: SymbolTable): State =
    a match { case Some(a_) => typeCheck(a_)(s) case None => State(s)}

  def typeCheck(a: AST)(s: SymbolTable): State = a match {
    case Program(children: Seq[TopLevel]) =>
      State.fold[AST](children, State(s), typeCheck)

    case ExternDeclaration(t, decList: Seq[Declarator]) =>
      State.fold[Declarator](decList, State(s), (addDeclarator(t, _)))

    case Declaration(t, decList) =>
      State.fold[Declarator](decList, State(s), (addDeclarator(t, _)))

    case FunctionDefinition(retType, functionDeclarator, body) => {
      val FunctionDeclarator(name, params) = functionDeclarator
      val st1 =
        if (s contains name)
          checkType(name, s.typeof(name), Func(params, retType))(s)  // check decl
        else
          addDeclarator(retType, functionDeclarator)(s) // Just add new Declaration
      for {
        s1 <- st1
        s2 <- addParams(params)(s1.addLevel.addFName(name))
        s3 <- typeCheck(body)(s2)
      } yield s3.removeLevel
    }

    case CompoundInstruction(decList, instrList) =>
      State.fold[AST](decList ++ instrList, State(s), typeCheck)

    case Assignment(i, e) => checkType(a, s.typeof(i), s.typeof(e))(s)

    case Condition(left, _, right) => for {
      s1 <- checkType(left, s.typeof(left), s.typeof(right))(s)
    } yield s1

    case SelectInstruction(cond, then, _else) => for {
      s1 <- typeCheck(cond)(s)
      s2 <- typeCheck(then)(s1.addLevel)
      s3 <- typeCheck(_else)(s2.removeLevel.addLevel)
    } yield s3.removeLevel

    case While(cond, instr ) => for {
      s1 <- typeCheck(cond)(s)
      s2 <- typeCheck(instr)(s1.addLevel)
    } yield s2.removeLevel

    case Do(cond, instr) => for {
      s1 <- typeCheck(cond)(s)
      s2 <- typeCheck(instr)(s1.addLevel)
    } yield s2.removeLevel

    case For(init, cond, fin, instr) => for {
      s1 <- typeCheck(init)(s)
      s2 <- typeCheck(cond)(s1)
      s3 <- typeCheck(fin)(s2)
      s4 <- typeCheck(instr)(s3.addLevel)
    } yield s4.removeLevel

    case Return(exp) => {
      val retType = for {
        r1 <- s.fname
        Func(_, r2) <- s.typeof(r1)
      } yield r2
      for {
        s1 <- checkType(a, s.typeof(exp), retType)(s)
        s2 <- typeCheck(exp)(s1)
      } yield s2
    }

    //For Expressions, we check the constituent parts to be Integer
    case Shiftive(first, rest) => State.foldExp(first, rest)(s)
    case Additive(first, rest) => State.foldExp(first, rest, s.typeof(first))(s)
    case Multiplicative(first, rest) => State.foldExp(first, rest)(s)
    case Minus(exp) => checkType(exp, s.typeof(exp), Integer)(s)

    case FunctionApplication(name, args) => {
      val first = checkType(name, s get name, s get name)(s) // Check presence of function name
      val params = s get name map (_.asInstanceOf[Func].args)
      params match {
        case Some(ps) if (args.length == ps.length) => // Check number of params
          (ps zip args).foldLeft(first) { // Check types of params
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
