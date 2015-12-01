package CSTRParser
/**
 * Created by mayankmandava on 10/14/15.
 */


import Lexemes._
import util.parsing.combinator.JavaTokenParsers


class Lexer extends JavaTokenParsers{

  protected override val whiteSpace = """(\s|//.*|(?m)/\*(\*(?!/)|[^*])*\*/)+""".r

  val `if`         = "if"                ^^^ IfKeyword
  val `else`       = "else"              ^^^ ElseKeyword
  val `while`      = "while"             ^^^ WhileKeyword
  val `do`         = "do"                ^^^ DoKeyword
  val `for`        = "for"               ^^^ ForKeyword
  val `return`     = "return"            ^^^ ReturnKeyword

  val int          = "int"               ^^^ IntType
  val string       = "string"            ^^^ StrType
  val extern       = "extern"            ^^^ ExternalDeclaration
  val arr          = "vec"               ^^^ ArrType

  val constInt    = super.wholeNumber    ^^  IntegerConstant
  val constSrt    = super.stringLiteral  ^^ StringConstant
  val constChar   = "'"~> ".".r <~"'"    ^^ (x => StringConstant(s""""$x""""))
  val constString = constSrt | constChar

  val plus         = "+"                 ^^^ PlusOp
  val minus        = "-"                 ^^^ MinusOp

  val assign       = "= "                ^^^ Assign

  val leftshift    = "<<"                ^^^ LeftShift
  val rightshift   = ">>"                ^^^ RightShift

  val equal        = "== "               ^^^ EqualOp
  val ne           = "!= "               ^^^ NEOp
  val lt           = "<"                 ^^^ LTOp
  val gt           = ">"                 ^^^ GTOp
  val le           = "<= "               ^^^ LEOp
  val ge           = ">= "               ^^^ GEOp

  val semicolon    = ";"                 ^^^ SemiColon
  val comma        = ","                 ^^^ Comma
  val lp           = "("                 ^^^ LeftParenthesis
  val rp           = ")"                 ^^^ RightParenthesis
  val lb           = "{"                 ^^^ LeftBraces
  val rb           = "}"                 ^^^ RightBraces
  val lbr          = "]"                 ^^^ LeftBrackets
  val rbr          = "["                 ^^^ RightBrackets

  val mult         = "*"                 ^^^ MultOp
  val div          = "/"                 ^^^ DivOp
  val mod          = "%"                 ^^^ ModOp


 val identifier : Parser[Ident] = super.ident ^^ Ident
 val keyword : Parser[Keyword] = `if` | `else` | `while` | `do` | `for` | `return`
 val `type` : Parser[Type] = int | string | arr | extern
 val const : Parser[Lexeme] = constInt | constString
 val plusminus : Parser[AdditiveOperator] = plus | minus
 val multdiv : Parser[MultiplicativeOperator] = mult | div | mod
 val shift : Parser[ShiftOperator] = leftshift | rightshift
 val comp : Parser[ComparisonOperator] = equal | ne | le | ge | lt | gt
 val punctuation : Parser[Lexeme] = semicolon | comma | lp | rp | lb | rb | lbr | rbr

  val lexeme : Parser[Lexeme] = keyword | `type` | const | plusminus | multdiv | shift | comp | assign | punctuation | identifier
}

