package CSTRParser

/**
 * Created by mayankmandava on 10/21/15.
 */

import AST._
import Lexemes._


object CSTRParser extends Lexer{

  def parse(s: String) = parseAll(program, s)

  def program: Parser[Seq[TopLevel]] = rep1(topLevel)

  def topLevel: Parser[TopLevel] = declaration | externDeclaration | functionDefinition

  def declaration: Parser[Declaration] = (`type` ~ rep1sep(declarator, comma)) <~ semicolon ^^ {
    case t ~ l => Declaration(t,  l)
  }
  def declarator: Parser[Declarator] = identifier <~ guard(comma | semicolon) | functionDeclarator <~ guard(comma | semicolon)

  def functionDeclarator: Parser[FunctionDeclarator] = identifier ~ (lp ~> parameterList.? <~ rp)  ^^ {
    case i ~ None => FunctionDeclarator(i, List[ParameterDeclaration]())
    case i ~ Some(x) => FunctionDeclarator(i, x)
  }
  def externDeclaration: Parser[ExternDeclaration] = extern ~> declaration ^^ {
    case Declaration(t, l) => ExternDeclaration(t, l)
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

  def selectInstruction: Parser[SelectInstruction] = `if` ~> condition ~ instruction ~ (`else` ~> instruction).? ^^ {
    case cnd ~ thn ~ els => SelectInstruction(cnd, thn, els)
  }
  def condition: Parser[Condition] = lp ~> shiftive ~ comp ~ shiftive <~ rp ^^ {
    case e1 ~ op ~ e2 => Condition(e1, op, e2)
  }

  def iterationInstruction: Parser[IterationInstruction] = whileInstruction | doInstruction | forInstruction
  def whileInstruction:Parser[While] = (`while` ~ lp) ~> condition ~ (rp ~> instruction)  ^^ {
    case c ~ i => While(c, i)
  }
  def doInstruction: Parser[Do] = `do` ~> instruction ~ ((`while` ~ lp) ~> condition <~ rp) ^^ {
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

  def shiftive: Parser[Shiftive] = additive ~ makeRepeater(additive, shift).* ^^ {
    case a ~ l => Shiftive(a, l)
  }

  def additive: Parser[Additive] = multiplicative ~ makeRepeater(multiplicative, plusminus).* ^^ {
    case a ~ l => Additive(a, l)
  }

  def multiplicative: Parser[Multiplicative] = unaryExpression ~ makeRepeater(unaryExpression, multdiv).* ^^ {
    case a ~ l => Multiplicative(a, l)
  }

  def unaryExpression: Parser[Unary] = postfixExpression | (minus ~> unaryExpression ^^ Minus)

  def postfixExpression: Parser[Postfix] = functionApplication | primaryExpression

  def functionApplication: Parser[FunctionApplication] = identifier ~ (lp ~> repsep(shiftive, comma) <~ rp) ^^ {
      case i ~ l => FunctionApplication(i, l)}

  def primaryExpression: Parser[PrimayExpression] = identifier | constInt | constString | lp ~> shiftive <~ rp
}
