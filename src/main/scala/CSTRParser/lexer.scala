package CSTRParser
/**
 * Created by mayankmandava on 10/14/15.
 */

import CSTRParser.lexemes._
import fastparse.all._

object Lexer {

  val wsComment = P( (CharsWhile(" \t\n\r".toSet, min = 1)) | comment)
  val comment = P( lineComment | blockComment )
  val lineComment = P( "//" ~ CharsWhile(_ != '\n'))
  val blockComment = P("/*" ~ blockCommentContent.rep ~ "*/")
  val blockCommentContent = P( CharsWhile(_ != '*') | "*" ~ !"/" )

  val lexeme = P( CONTROL | TYPE | CONST | MATH | EXPR | COMP | PUNCTUATION | IDENT )

  val CONTROL = P( IF | ELSE | WHILE | DO | FOR | RETURN)
  val TYPE = P( INT | STRING | EXTERN)
  val CONST = P( CONST_INT | CONST_STRING )
  val MATH = P( PLUS | MINUS | MULT | DIV | MOD)
  val EXPR = P( ASSIGN | LEFTSHIFT | RIGHTSHIFT )
  val COMP = P( EQUAL | NE | LT | GT | LE | GE )
  val PUNCTUATION = P( SEMICOLON | COMMA | LP | RP | LB | RB )

  val IF = P( "if" ).!.map(If_keyword)
  val ELSE = P( "else" ).!.map(Else_keyword)
  val WHILE = P( "while" ).!.map(While_keyword)
  val DO = P( "do" ).!.map(Do_keyword)
  val FOR = P( "for" ).!.map(For_keyword)
  val RETURN = P( "return" ).!.map(Return_keyword)

  val INT = P( "int" ).!.map(Integer_declaration)
  val STRING = P( "string" ).!.map(String_declaration)
  val EXTERN = P( "extern" ).!.map(External_declaration)

  val CONST_INT = P ( CharIn('0' to '9').rep(1) ).!.map((s:String) => Integer_constant(s.toInt))
  val CONST_STRING = P( "\"" ~ CharsWhile(_ != '"').! ~ "\"").map(String_constant)

  val PLUS = P("+").!.map(Additive_operator)
  val MINUS = P("-").!.map(Additive_operator)

  val ASSIGN = P("=").!.map(Expression)

  val LEFTSHIFT = P("<<").!.map(Expression)
  val RIGHTSHIFT = P(">>").!.map(Expression)

  val EQUAL = P("==").!.map(Comparison_operator)
  val NE = P("!=").!.map(Comparison_operator)
  val LT = P("<").!.map(Comparison_operator)
  val GT = P(">").!.map(Comparison_operator)
  val LE = P("<=").!.map(Comparison_operator)
  val GE = P(">=").!.map(Comparison_operator)

  val SEMICOLON = P(";").!.map(SemiColon)
  val COMMA = P(",").!.map(Comma)
  val LP = P( "(" ).!.map(Left_parenthesis)
  val RP = P( ")" ).!.map(Right_parenthesis)
  val LB = P( "{" ).!.map(Left_braces)
  val RB = P( "}" ).!.map(Right_braces)

  val MULT = P( "*" ).!.map(Multiplicative_operator)
  val DIV = P( "/" ).!.map(Multiplicative_operator)
  val MOD = P( "%" ).!.map(Multiplicative_operator)

  val IDENT      = P( (letter|"_") ~ (letter | digit | "_").rep ).!.map(Ident)
  val letter     = P( lowercase | uppercase )
  val lowercase  = P( CharIn('a' to 'z') )
  val uppercase  = P( CharIn('A' to 'Z') )
  val digit      = P( CharIn('0' to '9') )

  val program: P[Seq[Lexeme]] = P(wsComment.rep ~ lexeme.rep(sep = wsComment.rep, min = 1) ~ wsComment.rep ~ End)

  def main(args: Array[String]) {
    // The file to compile is passed as the fist argument
    if (args.length < 1) {
      println("No file passed")
      return
    }
    val fileName = args(0)
    val fileLines = io.Source.fromFile(fileName).getLines
    val fileContent = fileLines mkString "\n"
    val output = program parse fileContent
    output match {
      case fastparse.core.Result.Success(value, _) => for (line <- value) println(line)
      case _ => {
        val index:Int = output.index
        println("Error at position " + index + ": " +
          fileContent.substring(index, math.min(index + 20, fileContent.length - 1)))
      }
      case _ => println(output.asInstanceOf[Result.Failure].traced.trace)
    }
  }
}

