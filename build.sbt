name := "CSTRCompiler"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.scala-lang.modules" % "scala-parser-combinators_2.11" % "1.0.4"

libraryDependencies += "com.github.nikita-volkov" % "sext" % "0.2.4"

scalacOptions ++= Seq("-feature", "-language:higherKinds", "-language:implicitConversions")

