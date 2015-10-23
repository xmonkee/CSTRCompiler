package CSTRParser

/**
 * Created by mayankmandava on 10/21/15.
 */

object Parser extends Lexer {
  def program = rep1(lexeme)
  def scan(input: String) = parseAll(program, input)
}
