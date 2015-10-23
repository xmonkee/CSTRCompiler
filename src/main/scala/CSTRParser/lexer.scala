package CSTRParser
/**
 * Created by mayankmandava on 10/14/15.
 */

import CSTRParser.Lexemes._
import util.parsing.combinator.JavaTokenParsers


class Lexer extends JavaTokenParsers{

  val IF           = "if"                ^^^ IfKeyword
  val ELSE         = "else"              ^^^ ElseKeyword
  val WHILE        = "while"             ^^^ WhileKeyword
  val DO           = "do"                ^^^ DoKeyword
  val FOR          = "for"               ^^^ ForKeyword
  val RETURN       = "return"            ^^^ ReturnKeyword

  val INT          = "int"               ^^^ IntegerDeclaration
  val STRING       = "string"            ^^^ StringDeclaration
  val EXTERN       = "extern"            ^^^ ExternalDeclaration

  val CONST_INT    = super.wholeNumber   ^^ IntegerConstant
  val CONST_STRING = super.stringLiteral ^^ StringConstant

  val PLUS         = "+"                 ^^^ PlusOp
  val MINUS        = "-"                 ^^^ MinusOp

  val ASSIGN       = "= "                ^^^ Assignment

  val LEFTSHIFT    = "<<"                ^^^ LeftShift
  val RIGHTSHIFT   = ">>"                ^^^ RightShift

  val EQUAL        = "== "               ^^^ EqualOp
  val NE           = "!= "               ^^^ NEOp
  val LT           = "<"                 ^^^ LTOp
  val GT           = ">"                 ^^^ GTOp
  val LE           = "<= "               ^^^ LEOp
  val GE           = ">= "               ^^^ GEOp

  val SEMICOLON    = ";"                 ^^^ SemiColon
  val COMMA        = ","                 ^^^ Comma
  val LP           = "("                 ^^^ LeftParenthesis
  val RP           = ")"                 ^^^ RightParenthesis
  val LB           = "{"                 ^^^ LeftBraces
  val RB           = "}"                 ^^^ RightBraces
  val LBR          = "]"                 ^^^ LeftBrackets
  val RBR          = "["                 ^^^ RightBrackets

  val MULT         = "*"                 ^^^ MultOp
  val DIV          = "/"                 ^^^ DivOp
  val MOD          = "%"                 ^^^ ModOp


  val IDENT: Parser[Ident]         = super.ident ^^ Ident
  val KEYWORD: Parser[Keyword]     = IF                    | ELSE         | WHILE  | DO  | FOR | RETURN
  val TYPE: Parser[Lexeme]         = INT                   | STRING       | EXTERN
  val CONST: Parser[Lexeme]        = CONST_INT             | CONST_STRING
  val MATH: Parser[Lexeme]         = PLUS                  | MINUS        | MULT   | DIV | MOD
  val SHIFT: Parser[Lexeme]        = LEFTSHIFT             | RIGHTSHIFT
  val COMP : Parser[Lexeme]        = EQUAL                 | NE           | LE     | GE  | LT  | GT
  val PUNCTUATION: Parser[Lexeme]  = SEMICOLON             | COMMA        | LP     | RP  | LB  | RB     | LBR | RBR

  val lexeme: Parser[Lexeme]       = KEYWORD | TYPE | CONST | MATH | SHIFT | COMP | ASSIGN | PUNCTUATION | IDENT
}

