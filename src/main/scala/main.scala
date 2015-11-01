
import CSTRParser.{AST, TypeChecker}

import scala.util.{Try, Success, Failure}

import sext._

/**
 * Created by mayankmandava on 10/15/15.
 */
object main {


  implicit def parseResultToTry[A](p: CSTRParser.CSTRParser.ParseResult[A]):Try[A] = p match {
    case CSTRParser.CSTRParser.Success(v, _) => Success(v)
    case CSTRParser.CSTRParser.Failure(e, m) => Failure(new Exception(e))
    case CSTRParser.CSTRParser.Error(e, m) => Failure(new Exception(e))
  }

  def main(args: Array[String]): Unit = {
    val output = for {
      fileName <- Try(args(0)) recoverWith {
        case e: Exception => Failure(new Exception("No filename given"))}
      fileLines <- Try(io.Source.fromFile(fileName).getLines)
      fileContent <- Try(fileLines.mkString("\n"))
      ast <- CSTRParser.CSTRParser.parse(fileContent)
    } yield ast

    output match {
      case Success(v) => {
        println(v.treeString)
        println;
        println(TypeChecker.State().typeCheck(v).errors.reverse map ("Type Error: " + _) mkString "\n")
        println;
      }
      case Failure(e) => println("Error: " + e.getMessage)
    }

  }
}
