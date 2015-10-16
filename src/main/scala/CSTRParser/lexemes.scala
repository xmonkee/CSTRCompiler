package CSTRParser

/**
 * Created by mayankmandava on 10/14/15.
 */

object Lexemes {

  trait Lexeme

  case class Ident(s: String) extends Lexeme

  case class External_declaration(s: String) extends Lexeme
  case class Integer_declaration(s: String) extends Lexeme
  case class String_declaration(s: String) extends Lexeme

  case class Integer_constant(i: Int) extends Lexeme
  case class String_constant(s: String) extends Lexeme

  trait Additive_operator
  case class Plus_op(s: String) extends Lexeme with Additive_operator
  case class Minus_op(s: String) extends Lexeme with Additive_operator

  trait Shift_operator
  case class Left_shift(s: String) extends Lexeme with Shift_operator
  case class Right_shift(s: String) extends Lexeme with Shift_operator

  case class Assignment(s: String) extends Lexeme

  trait Comparison_operator
  case class Equal_op(s: String) extends Lexeme with Comparison_operator
  case class NE_op(s: String) extends Lexeme with Comparison_operator
  case class LT_op(s: String) extends Lexeme with Comparison_operator
  case class GT_op(s: String) extends Lexeme with Comparison_operator
  case class LE_op(s: String) extends Lexeme with Comparison_operator
  case class GE_op(s: String) extends Lexeme with Comparison_operator

  trait Multiplicative_operator
  case class Mult_op(s: String) extends Lexeme with Multiplicative_operator
  case class Div_op(s: String) extends Lexeme with Multiplicative_operator
  case class Mod_op(s: String) extends Lexeme with Multiplicative_operator {
    // Have to override this one because printing % sign without escaping throws error
    override def toString = "Mod_op(%%)"
  }

  trait Keyword
  case class If_keyword(s: String) extends Lexeme with Keyword
  case class Else_keyword(s: String) extends Lexeme with Keyword
  case class While_keyword(s: String) extends Lexeme with Keyword
  case class Do_keyword(s: String) extends Lexeme with Keyword
  case class For_keyword(s: String) extends Lexeme with Keyword
  case class Return_keyword(s: String) extends Lexeme with Keyword

  case class Left_parenthesis(s: String) extends Lexeme
  case class Right_parenthesis(s: String) extends Lexeme
  case class Left_braces(s: String) extends Lexeme
  case class Right_braces(s: String) extends Lexeme
  case class Left_brackets(s: String) extends Lexeme
  case class Right_brackets(s: String) extends Lexeme
  case class Comma(s: String) extends Lexeme
  case class SemiColon(s: String) extends Lexeme

}
