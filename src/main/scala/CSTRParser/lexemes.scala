package CSTRParser

/**
 * Created by mayankmandava on 10/14/15.
 */

object Lexemes {

  trait Lexeme

  case class Ident(s: String) extends Lexeme

  case object ExternalDeclaration extends Lexeme
  case object IntegerDeclaration extends Lexeme
  case object StringDeclaration extends Lexeme

  trait Type extends Lexeme
  case class IntegerConstant(s: String) extends Type
  case class StringConstant(s: String) extends Type

  trait AdditiveOperator extends Lexeme
  case object PlusOp extends AdditiveOperator
  case object MinusOp extends AdditiveOperator

  trait ShiftOperator extends Lexeme
  case object LeftShift extends ShiftOperator
  case object RightShift extends ShiftOperator

  case object Assignment extends Lexeme

  trait ComparisonOperator extends Lexeme
  case object EqualOp extends ComparisonOperator
  case object NEOp extends ComparisonOperator
  case object LTOp extends ComparisonOperator
  case object GTOp extends ComparisonOperator
  case object LEOp extends ComparisonOperator
  case object GEOp extends ComparisonOperator

  trait MultiplicativeOperator extends Lexeme
  case object MultOp extends MultiplicativeOperator
  case object DivOp extends MultiplicativeOperator
  case object ModOp extends MultiplicativeOperator {
    override def toString = "ModOp(%%)"
  }

  trait Keyword extends Lexeme
  case object IfKeyword extends Keyword
  case object ElseKeyword extends Keyword
  case object WhileKeyword extends Keyword
  case object DoKeyword extends Keyword
  case object ForKeyword extends Keyword
  case object ReturnKeyword extends Keyword

  case object LeftParenthesis extends Lexeme
  case object RightParenthesis extends Lexeme
  case object LeftBraces extends Lexeme
  case object RightBraces extends Lexeme
  case object LeftBrackets extends Lexeme
  case object RightBrackets extends Lexeme
  case object Comma extends Lexeme
  case object SemiColon extends Lexeme

}
