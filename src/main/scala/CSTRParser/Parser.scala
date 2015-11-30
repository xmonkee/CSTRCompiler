package CSTRParser

/**
 * Created by mayankmandava on 10/21/15.
 */

import AST._
import Lexemes._


object CSTRParser extends Lexer{

  def parse(s: String) = parseAll(program, s)

  def program: Parser[Program] = rep1(topLevel) ^^ Program

  def topLevel: Parser[TopLevel] = declaration | externDeclaration | functionDefinition

  def declaration: Parser[Declaration] = (`type` ~ rep1sep(declarator, comma)) <~ semicolon ^^ {
    case t ~ l => Declaration(t,  l)
  }
  def declarator: Parser[Declarator] = identifier <~ guard(comma | semicolon) | functionDeclarator <~ guard(comma | semicolon)

  def functionDeclarator: Parser[FunctionDeclarator] = identifier ~ (lp ~> parameterList.? <~ rp)  ^^ {
    case i ~ None => FunctionDeclarator(i, List[ParameterDeclaration]())
    case i ~ Some(x) => FunctionDeclarator(i, x)
  }
  def externDeclaration: Parser[Declaration] = extern ~> declaration ^^ {
    case Declaration(t, l) => Declaration(t, l)
  }
  def functionDefinition: Parser[FunctionDefinition] = `type` ~ functionDeclarator ~ compoundInstruction ^^ {
    case t ~ f ~ c => FunctionDefinition(t, f, c)
  }
  def parameterList: Parser[List[ParameterDeclaration]] = rep1sep(`type` ~ identifier, comma) ^^ {
    _ map { case t ~ i => ParameterDeclaration(t, i)}
  }

  def compoundInstruction: Parser[CompoundInstruction] =
    lb ~> (declaration.* ~  instruction.*) <~ rb ^^ {
      case d ~ i => CompoundInstruction(d, i)
    }

  def instruction: Parser[Instruction] = compoundInstruction | expressionInstruction | iterationInstruction | selectInstruction | jumpInstruction | emptyInstruction
  def expressionInstruction: Parser[ExpressionInstruction] = (shiftive <~ semicolon) | (assignment <~ semicolon)
  def assignment: Parser[Assignment] = identifier ~ (assign ~> shiftive) ^^ {
    case i ~ e => Assignment(i, e)
  }

  def selectInstruction: Parser[SelectInstruction] = `if` ~> (lp ~> condition <~ rp) ~ instruction ~ (`else` ~> instruction).? ^^ {
    case cnd ~ thn ~ els => SelectInstruction(cnd, thn, els)
  }
  def condition: Parser[Condition] =  shiftive ~ comp ~ shiftive ^^ {
    case e1 ~ op ~ e2 => Condition(e1, op, e2)
  }

  def iterationInstruction: Parser[IterationInstruction] = whileInstruction | doInstruction | forInstruction
  def whileInstruction:Parser[While] = `while` ~> (lp ~> condition <~ rp) ~ instruction  ^^ {
    case c ~ i => While(c, i)
  }
  def doInstruction: Parser[Do] = `do` ~> instruction ~ (`while` ~> (lp ~> condition <~ rp) <~ semicolon) ^^ {
    case i ~ c => Do(c, i)
  }
  def forInstruction: Parser[For] = `for` ~> (lp ~> assignment <~ semicolon) ~
    (condition <~ semicolon) ~ (assignment <~ rp) ~ instruction ^^ {
    case a1 ~ c ~ a2 ~ i => For(a1, c, a2, i)
  }

  def jumpInstruction: Parser[Return] = `return` ~> shiftive <~ semicolon ^^ Return

  def emptyInstruction: Parser[EmptyInstruction] = semicolon ^^^ EmptyInstruction()

  def makeRepeater[C, S](child: Parser[C], separator: Parser[S]): Parser[(S, C)] = separator ~ child ^^ {
      case s ~ c => (s, c)
    }

  def shiftive: Parser[Expression] = additive ~ makeRepeater(additive, shift).* ^^ {
    case a ~ Nil => a
    case a ~ l => Shiftive(a, l)
  }

  def additive: Parser[Expression] = multiplicative ~ makeRepeater(multiplicative, plusminus).* ^^ {
    case a ~ Nil => a
    case a ~ l => Additive(a, l)
  }

  def multiplicative: Parser[Expression] = unaryExpression ~ makeRepeater(unaryExpression, multdiv).* ^^ {
    case a ~ Nil => a
    case a ~ l => Multiplicative(a, l)
  }

  def unaryExpression: Parser[Expression] = postfixExpression | (minus ~> unaryExpression ^^ Minus)

  def postfixExpression: Parser[Expression] = functionApplication | primaryExpression

  def functionApplication: Parser[Expression] = identifier ~ (lp ~> repsep(shiftive, comma) <~ rp) ^^ {
      case i ~ l => FunctionApplication(i, l)}

  def primaryExpression: Parser[Expression] = identifier | constInt | constString | lp ~> shiftive <~ rp
}
