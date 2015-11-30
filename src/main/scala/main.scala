
import CSTRParser.{AST, TypeChecker, IRGen}
import scala.util.{Try, Success, Failure}
import scalaz._
import Scalaz._


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
        val typeErrors = (TypeChecker.State() flatMap TypeChecker.typeCheck(v) run)._1
        typeErrors foreach (e => println (s"Type Error: $e\n"))
        if (typeErrors.length == 0) {
          val pathname = args(0)
          import java.nio.file._
          val path = Paths.get(pathname)
          val filename = path.getFileName.toString
          val progName = filename.replaceAll("\\.[^.]*$", "")
          import java.io._
          val pw = new PrintWriter(new File(progName + ".j"))
          pw.write(new IRGen(progName).genProgram(v))
          pw.close
        }
      }
      case Failure(e) => println("Error: " + e.getMessage)
    }

  }
}
