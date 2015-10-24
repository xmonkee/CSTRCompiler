
import scala.util.{Try, Success, Failure}

/**
 * Created by mayankmandava on 10/15/15.
 */
object main {

  import sext._

  def main(args: Array[String]): Unit = {
    val output = for {
      fileName <- Try(args(0)) recoverWith {
        case e: Exception => Failure(new Exception("No filename given"))}
      fileLines <- Try(io.Source.fromFile(fileName).getLines)
      fileContent <- Try(fileLines.mkString("\n"))
      lexemes <- Try(CSTRParser.CSTRParser.parse(fileContent))
    } yield lexemes

    output match {
      case Success(v) => println(v.treeString)
      case Failure(e) => printf("Error: " + e.getMessage)
    }

  }
}
