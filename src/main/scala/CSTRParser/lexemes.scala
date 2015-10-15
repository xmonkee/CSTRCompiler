package CSTRParser

/**
 * Created by mayankmandava on 10/14/15.
 */

package lexemes {

  sealed abstract class Lexeme

  sealed abstract class Keyword extends Lexeme

  case class Ident(s: String) extends Lexeme

  case class External_declaration(s: String) extends Lexeme

  case class Integer_declaration(s: String) extends Lexeme

  case class String_declaration(s: String) extends Lexeme

  case class Integer_constant(i: Int) extends Lexeme

  case class String_constant(s: String) extends Lexeme

  case class Additive_operator(op: String) extends Lexeme

  case class Expression(op: String) extends Lexeme

  case class Comparison_operator(op: String) extends Lexeme

  case class Multiplicative_operator(op: String) extends Lexeme

  case class If_keyword(s: String) extends Keyword

  case class Else_keyword(s: String) extends Keyword

  case class While_keyword(s: String) extends Keyword

  case class Do_keyword(s: String) extends Keyword

  case class For_keyword(s: String) extends Keyword

  case class Return_keyword(s: String) extends Keyword

  case class Left_parenthesis(s: String) extends Lexeme

  case class Right_parenthesis(s: String) extends Lexeme

  case class Left_braces(s: String) extends Lexeme

  case class Right_braces(s: String) extends Lexeme

  case class Comma(s: String) extends Lexeme

  case class SemiColon(s: String) extends Lexeme

}
