
import scala.util.{Try, Success, Failure}
import CSTRParser.Lexer.scan

/**
 * Created by mayankmandava on 10/15/15.
 */
object main {

  def main(args: Array[String]): Unit = {
    val output: Try[String] = for {
      fileName <- Try(args(0)) recoverWith {
        case e: Exception => Failure(new Exception("No filename given"))}
      fileLines <- Try(io.Source.fromFile(fileName).getLines)
      fileContent <- Try(fileLines.mkString("\n"))
      lexemes <- scan(fileContent)
      out <- Try(lexemes.mkString("\n"))
    } yield out

    output match {
      case Success(v) => printf(v)
      case Failure(e) => printf("Error: " + e.getMessage)
    }

  }
}
