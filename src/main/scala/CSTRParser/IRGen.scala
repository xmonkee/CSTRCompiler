package CSTRParser

import AST._
import Lexemes._
import collection.mutable.{Stack, Map}


/**
 * Created by mayankmandava on 11/26/15.
 */
class IRGen(progName: String) {

/*  The IR Language
    Targeting the JVM using the Jasmine Assembler
*/

  // Types used here

  type Offset = Int
  trait Kind
  case object Local extends Kind
  case object Static extends Kind
  case class Entry(offset: Offset, kind: Kind, type_ : Type)

  class State {
    type Table = Map[String, Entry]
    var st: Stack[Table] = new Stack
    var funcs: Map[String, (Type, Seq[Type])] = Map.empty
    var output: String = "" // Final Output
    var totalOffset = 0
    var ftype: Type = IntegerDeclaration
    def addLevel = st.push(Map.empty)
    def removeLevel = st.pop
    def addStatic(type_ : Type, name: String) {
      addLine(s".field static $name ${jtype(type_)}")
      st.last.update(name, Entry(0, Static, type_))
    }
    def addFunc(type_ : Type, name: String, params: Seq[ParameterDeclaration]) = {
      funcs.update(name, (type_, params map (_.`type`)))
    }
    def addVar(type_ : Type, name: String) = {
      st.head.update(name, Entry(totalOffset, Local, type_))
      totalOffset += 1
    }
    def lookup(name: String, table: Stack[Table] = st) : Entry =
      table.head.getOrElse(name, lookup(name, table.tail))
    def addLine: Unit = addLine("")
    def addLine(line: String, marker: Option[Int] = None): Unit = {
      val indent = st.length
      marker match {
        case None => output += "  " * indent + (line + "\n")
        case Some(m) => output = output.substring(0, m) + "  "*indent + line + "\n" + output.substring(m)
      }
    }

    var labelCount = 0
    def newLabel = {
      labelCount += 1
      labelCount.toString
    }

    override def toString: String = st.toString
  }

  val state: State = new State

  //Helper functions

  def jtype (type_ : Type) : String = type_ match {
    case IntegerDeclaration => "I"
    case StringDeclaration => "Ljava/lang/String;"
    case _ => ""
  }


  def load_store(type_ : Type, ls: String, offset: Offset): String = {
    val prefix = type_ match {
      case IntegerDeclaration => "i"
      case StringDeclaration => "a"
    }
    val index = offset match {
      case _ if offset <= 3 => s"_$offset"
      case _ => s" $offset"
    }
    prefix + ls + index
  }

  def icmp(op: ComparisonOperator) = op match {
    case EqualOp => "if_icmpeq"
    case NEOp => "if_icmpne"
    case LTOp => "if_icmplt"
    case GTOp => "if_icmpgt"
    case LEOp => "if_icmple"
    case GEOp => "if_icmpge"
  }

  def condition(cond: Condition, yes: String, no: String) = {
    val Condition(left, op, right) = cond
    typeof(left) match {
      case IntegerDeclaration => {
        genExp(left)
        genExp(right)
        state.addLine(s"${icmp(op)} $yes")
        state.addLine(s"goto $no")
      }
      case StringDeclaration => Unit
    }
  }

  def typeof(exp: Expression): Type = exp match {
    case StringConstant(_) => StringDeclaration
    case IntegerConstant(_) => IntegerDeclaration
    case FunctionApplication(Ident(name), args) => state.funcs.get(name).get._1
    case Ident(name) => state.lookup(name).type_
    case Additive(first, rest) => typeof(first)
    case _ => IntegerDeclaration
  }

  // Code Generation

  def genProgram(program: AST.Program): String = {
    state.addLevel
    state.output +=
      s"""
        |.class public $progName
        |.super java/lang/Object
        |""".stripMargin
    program.children map genIR
    state.output +=
      s"""
         |    .method public static main([Ljava/lang/String;)V
         |    .limit locals 1
         |    .limit stack 1
         |      invokestatic $progName/_main()I
         |      pop
         |      return
         |    .end method
         |
         |    .method public static printd(I)I
         |    .limit locals 1
         |    .limit stack 2
         |      getstatic      java/lang/System/out Ljava/io/PrintStream;
         |      iload_0
         |      invokevirtual  java/io/PrintStream/println(I)V
         |      iconst_0
         |      ireturn
         |    .end method
       """.stripMargin
    state.removeLevel
    state.output
  }

  def genIR(ast: AST): Unit = ast match {
    case Declaration(type_, declaratorList) =>
      for (declerator <- declaratorList) declerator match {
        case Ident(s) => state.addStatic(type_, s)
        case FunctionDeclarator(Ident(name), params) => state.addFunc(type_, name, params)
      }
    case FunctionDefinition(rtype, FunctionDeclarator(Ident(name), params), body) => {
      state.addLevel
      state.ftype = rtype
      val types = for (ParameterDeclaration(ptype, Ident(name)) <- params) yield {
        state.addVar(ptype, name)
        jtype(ptype)
      }
      val name_ = if(name == "main") "_main" else name
      state.addLine
      state.addLine(s".method public static $name_(${types mkString ","})${jtype(rtype)}")
      state.addLine(".limit stack 100")
      val marker = state.output.length
      genIR(body)
      state.addLine(s".limit locals ${state.totalOffset}", Some(marker))
      state.addLine(".end method")

      state.removeLevel
      state.totalOffset = 0
    }
    case CompoundInstruction(decls, instrs) => {
      state.addLevel
      for (Declaration(type_, vars) <- decls)
        for (Ident(name) <- vars) state.addVar(type_, name)
      instrs foreach genIR
      state.removeLevel
    }
    case Assignment(Ident(name), exp) => {
      genExp(exp)
      val Entry(offset, kind, type_) = state.lookup(name)
      kind match {
        case Local => state.addLine(load_store(type_, "store", offset))
        case Static => state.addLine(s"putstatic $progName/$name ${jtype(type_)}")
      }
    }
    case Return(exp) => {
      genExp(exp)
      state.ftype match {
        case IntegerDeclaration => state.addLine("ireturn")
        case StringDeclaration => state.addLine("areturn")
      }
    }

    case SelectInstruction(cond, then, _else) => {
      val label = state.newLabel
      val tlabel = "T"+label
      val flabel = "F"+label
      val nlabel = "N"+label
      condition(cond, tlabel, flabel)
      state.addLine(s"$tlabel:")
      genIR(then)
      state.addLine(s"goto $nlabel")
      state.addLine(s"$flabel:")
      if (_else.isDefined) genIR(_else.get)
      state.addLine(s"$nlabel:")
    }


    case x : Expression => {
      genExp(x)
      state.addLine("pop")
    }
    case _ => ()
  }
  def genExp(exp: Expression): Unit = exp match {
    case StringConstant(s) => state.addLine(s"ldc $s")
    case IntegerConstant(s) => state.addLine(s"ldc $s")
    case Ident(name) => {
      val Entry(offset, kind, type_) = state.lookup(name)
      kind match {
        case Local => state.addLine(load_store(type_, "load", offset))
        case Static => state.addLine(s"getstatic $progName/$name ${jtype(type_)}")
      }
    }
    case Minus(unary) => state.addLine("ineg")
    case Shiftive(first, rest) => {
      genExp(first)
      for ((op, e) <- rest) op match {
        case LeftShift => {genExp(e); state.addLine("ishl")}
        case RightShift => {genExp(e); state.addLine("ishr")}
      }
    }
    case Additive(first, rest) => {
      genExp(first)
      typeof(first) match {
        case IntegerDeclaration =>
          for ((op, e) <- rest) op match {
            case PlusOp => {genExp(e); state.addLine("iadd")}
            case MinusOp => {genExp(e); state.addLine("isub")}
          }
        case StringDeclaration =>
          for ((op, e) <- rest) {
            genExp(e);
            state.addLine("invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;")}
          }
      }
    case Multiplicative(first, rest) => {
      genExp(first)
      for ((op, e) <- rest) op match {
        case MultOp => {genExp(e); state.addLine("imul")}
        case DivOp => {genExp(e); state.addLine("idiv")}
        case ModOp => {genExp(e); state.addLine("irem")}
      }
    }
    case FunctionApplication(Ident(name), args) => {
      args foreach genExp
      val argtypes = state.funcs.get(name).get._2 map jtype mkString ","
      val retType = state.funcs.get(name).get._1
      state.addLine(s"invokestatic $progName/$name($argtypes)${jtype(retType)}")
    }
    case _ => Unit
  }


}
