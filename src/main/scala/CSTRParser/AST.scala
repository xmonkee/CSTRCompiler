package CSTRParser

import CSTRParser.Lexemes.{ShiftOperator, ComparisonOperator}

sealed abstract trait AST
object AST {
  case class Program(children: List[TopLevel]) extends AST

  case class TopLevel() extends AST
  case class ExternDeclaration(_type: Lexemes.Type, declarationList: Seq[Declarator]) extends TopLevel
  case class FunctionDefinition(_type: Lexemes.Type, name: String, body: CompoundInstruction) extends TopLevel
  case class Declaration(t: Lexemes.Type, l: Seq[Declarator]) extends TopLevel

  case class Declarator() extends AST
  case class Variable(name: String) extends Declarator
  case class FunctionDeclerator(name: String, params: Seq[ParameterDeclaration]) extends Declarator

  case class ParameterDeclaration(_type: Lexemes.Type, name: String) extends AST

  case class Instruction() extends AST
  case class CompoundInstruction(declerationList: Seq[Declaration], instructionList: Seq[Instruction]) extends Instruction
  case class ExpressionInstruction() extends Instruction
  case class IterationInstruction() extends Instruction
  case class SelectInstruction() extends Instruction
  case class JumpInstruction() extends Instruction

  case class Expression(rest: Option[(ShiftOperator, ExpressionAdditive)]) extends ExpressionInstruction
  case class Assignment(variable: Variable, expression: Expression) extends ExpressionInstruction

  case class CondInstruction(cond: Condition, then: Instruction, _else: Option[Instruction]) extends SelectInstruction
  case class Condition(left: Expression, op: ComparisonOperator, right: Expression) extends AST

  case class While(cond: Condition, instruction: Instruction) extends IterationInstruction
  case class Do(cond: Condition, instruction: Instruction) extends IterationInstruction
  case class For(init: Assignment, cond: Condition, _final: Assignment) extends IterationInstruction

  case class Return(expr: Expression) extends JumpInstruction

}
