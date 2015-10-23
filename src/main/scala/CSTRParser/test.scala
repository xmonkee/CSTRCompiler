package CSTRParser

import util.parsing.combinator.JavaTokenParsers

class test extends JavaTokenParsers{
  trait Lexeme
  case class Ident(s: String) extends Lexeme
  case class Integer(s: String) extends Lexeme

  def IDENT: Parser[Ident] = super.ident ^^ Ident
  def INTEGER: Parser[Integer] = super.wholeNumber ^^ Integer
  def LEXEME: Parser[Lexeme] = IDENT | INTEGER
}
