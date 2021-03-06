package CSTRParser

import Lexemes._

sealed abstract trait AST

object AST {

  abstract trait TopLevel extends AST
  abstract trait Declarator extends AST
  abstract trait Instruction extends AST
  abstract trait ExpressionInstruction extends Instruction
  abstract trait IterationInstruction extends Instruction
  abstract trait JumpInstruction extends Instruction
  abstract trait Expression extends ExpressionInstruction

  case class Program(children: Seq[TopLevel]) extends AST
  case class ExternDeclaration(`type`: Type, declaratorList: Seq[Declarator]) extends TopLevel
  case class FunctionDefinition(`type`: Type, functionDeclarator: FunctionDeclarator, body: CompoundInstruction) extends TopLevel
  case class Declaration(`type`: Type, declaratorList: Seq[Declarator]) extends TopLevel
  case class FunctionDeclarator(name: Ident, params: Seq[ParameterDeclaration]) extends Declarator
  case class ParameterDeclaration(`type`: Lexemes.Type, name: Ident) extends AST

  case class EmptyInstruction() extends Instruction
  case class CompoundInstruction(declerationList: Seq[Declaration], instructionList: Seq[Instruction]) extends Instruction
  case class Assignment(variable: Ident, expression: Expression) extends ExpressionInstruction

  case class Condition(left: Expression, op: ComparisonOperator, right: Expression) extends AST
  case class SelectInstruction(cond: Condition, then: Instruction, _else : Option[Instruction]) extends Instruction
  case class While(cond: Condition, instruction: Instruction) extends IterationInstruction
  case class Do(cond: Condition, instruction: Instruction) extends IterationInstruction
  case class For(init: Assignment, cond: Condition, `final`: Assignment, instr: Instruction) extends IterationInstruction
  case class Return(expr: Expression) extends JumpInstruction

  case class Shiftive(first: Expression, rest: Seq[(ShiftOperator, Expression)]) extends Expression with PrimaryExpression
  case class Additive(first: Expression, rest: Seq[(AdditiveOperator, Expression)]) extends Expression
  case class Multiplicative(first: Expression, rest: Seq[(MultiplicativeOperator, Expression)]) extends Expression
  abstract trait Unary extends Expression
  case class Minus(unary: Expression) extends Unary
  abstract trait Postfix extends Unary
  abstract trait PrimaryExpression extends Postfix
  case class FunctionApplication(name: Ident, args: Seq[Expression]) extends Postfix
}
