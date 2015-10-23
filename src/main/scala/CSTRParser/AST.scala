package CSTRParser

import CSTRParser.Lexemes._

sealed abstract trait AST
object AST {
  case class Program(children: List[TopLevel]) extends AST

  case trait TopLevel extends AST
  case class ExternDeclaration(_type: Lexemes.Type, declarationList: Seq[Declarator]) extends TopLevel
  case class FunctionDefinition(_type: Lexemes.Type, name: Ident, body: CompoundInstruction) extends TopLevel
  case class Declaration(t: Lexemes.Type, l: Seq[Declarator]) extends TopLevel

  case trait Declarator extends AST
  case class Variable(name: Ident) extends Declarator
  case class FunctionDeclerator(name: Ident, params: Seq[ParameterDeclaration]) extends Declarator

  case class ParameterDeclaration(_type: Lexemes.Type, name: Ident) extends AST

  case trait Instruction extends AST
  case class CompoundInstruction(declerationList: Seq[Declaration], instructionList: Seq[Instruction]) extends Instruction
  case class ExpressionInstruction() extends Instruction
  case class IterationInstruction() extends Instruction
  case class SelectInstruction() extends Instruction
  case class JumpInstruction() extends Instruction

  case class Assignment(variable: Variable, expression: Expression) extends ExpressionInstruction

  case class CondInstruction(cond: Condition, then: Instruction, _else: Option[Instruction]) extends SelectInstruction
  case class Condition(left: Expression, op: ComparisonOperator, right: Expression) extends AST

  case class While(cond: Condition, instruction: Instruction) extends IterationInstruction
  case class Do(cond: Condition, instruction: Instruction) extends IterationInstruction
  case class For(init: Assignment, cond: Condition, _final: Assignment) extends IterationInstruction

  case class Return(expr: Expression) extends JumpInstruction

  case trait Expression extends AST
  case class Additive(left: Expression, op: AdditiveOperator, right: Expression) extends Expression
  case class Multiplicative(left: Expression, op: MultiplicativeOperator, right: Expression) extends Expression
  case class Minus(expr: Expression) extends Expression
  case class FunctionApplication(name: Ident, args: Seq[Expression]) extends Expression


}
