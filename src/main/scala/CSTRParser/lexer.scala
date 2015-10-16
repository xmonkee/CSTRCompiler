package CSTRParser
/**
 * Created by mayankmandava on 10/14/15.
 */

import CSTRParser.Lexemes._
import fastparse.all._

import scala.collection.mutable
import scala.util.{Failure, Success, Try}

object Lexer {

  case class InvalidLexemeException(message: String) extends Exception(message: String)

  val logged = mutable.Buffer.empty[String]
  implicit val logger = fastparse.Logger(logged.append(_))

  val wsComment = P( (CharsWhile(" \t\n\r".toSet, min = 1)) | comment).log()
  val comment = P( lineComment | blockComment )
  val lineComment = P( "//" ~ CharsWhile(_ != '\n'))
  val blockComment = P("/*" ~ blockCommentContent.rep ~ "*/")
  val blockCommentContent = P( CharsWhile(_ != '*') | "*" ~ !"/" )

  val lexeme = P( CONTROL | TYPE | CONST | MATH | SHIFT | COMP | ASSIGN | PUNCTUATION | IDENT ).log()

  val CONTROL = P( IF | ELSE | WHILE | DO | FOR | RETURN).log()
  val TYPE = P( INT | STRING | EXTERN).log()
  val CONST = P( CONST_INT | CONST_STRING ).log()
  val MATH = P( PLUS | MINUS | MULT | DIV | MOD).log()
  val SHIFT = P( LEFTSHIFT | RIGHTSHIFT ).log()
  val COMP = P( EQUAL | NE | LE | GE  | LT | GT).log()
  val PUNCTUATION = P( SEMICOLON | COMMA | LP | RP | LB | RB | LBR | RBR ).log()

  val IF = P( "if" ).!.map(If_keyword).log()
  val ELSE = P( "else" ).!.map(Else_keyword).log()
  val WHILE = P( "while" ).!.map(While_keyword).log()
  val DO = P( "do" ).!.map(Do_keyword).log()
  val FOR = P( "for" ).!.map(For_keyword).log()
  val RETURN = P( "return" ).!.map(Return_keyword).log()

  val INT = P( "int".! ~ &(wsComment)).map(Integer_declaration).log()
  val STRING = P( "string" ).!.map(String_declaration).log()
  val EXTERN = P( "extern" ).!.map(External_declaration).log()

  val CONST_INT = P ( "-".? ~ CharIn('0' to '9').rep(1) ).!.map((s:String) => Integer_constant(s.toInt)).log()
  val CONST_STRING = P( "\"" ~ CharsWhile(_ != '"').! ~ "\"").map(String_constant).log()

  val PLUS = P("+").!.map(Plus_op).log()
  val MINUS = P("-").!.map(Minus_op).log()

  val ASSIGN = P("=").!.map(Assignment).log()

  val LEFTSHIFT = P("<<").!.map(Left_shift).log()
  val RIGHTSHIFT = P(">>").!.map(Right_shift).log()

  val EQUAL = P("==").!.map(Equal_op).log()
  val NE = P("!=").!.map(NE_op).log()
  val LT = P("<").!.map(LT_op).log()
  val GT = P(">").!.map(GT_op).log()
  val LE = P("<=").!.map(LE_op).log()
  val GE = P(">=").!.map(GE_op).log()

  val SEMICOLON = P(";").!.map(SemiColon).log()
  val COMMA = P(",").!.map(Comma).log()
  val LP = P( "(" ).!.map(Left_parenthesis).log()
  val RP = P( ")" ).!.map(Right_parenthesis).log()
  val LB = P( "{" ).!.map(Left_braces).log()
  val RB = P( "}" ).!.map(Right_braces).log()
  val LBR = P( "]" ).!.map(Left_brackets).log()
  val RBR = P( "[" ).!.map(Right_brackets).log()

  val MULT = P( "*" ).!.map(Mult_op).log()
  val DIV = P( "/" ).!.map(Div_op).log()
  val MOD = P( "%" ).!.map(Mod_op).log()

  val IDENT      = P( (letter|"_") ~ (letter | digit | "_").rep ).!.map(Ident).log()
  val letter     = P( lowercase | uppercase )
  val lowercase  = P( CharIn('a' to 'z') )
  val uppercase  = P( CharIn('A' to 'Z') )
  val digit      = P( CharIn('0' to '9') )

  val program: P[Seq[Lexeme]] = P(wsComment.rep ~ lexeme.rep(sep = wsComment.rep, min = 1) ~ wsComment.rep)

  def scan(fileContent: String): Try[Seq[String]] = {


    val output = program parse fileContent

//    printf(logged.mkString("\n"))

    output match {
      case fastparse.core.Result.Success(lexemes, _) => Try(lexemes map (_.toString))
      case _ => Failure(
        new InvalidLexemeException(
          "Scanning at position " +
            output.index
            + ": " + fileContent.substring(
              output.index, math.min(output.index + 20, fileContent.length - 1)))
       )
    }
  }
}

