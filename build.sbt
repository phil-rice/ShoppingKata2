name := "shoppingKata2"

version := "0.1"

scalaVersion := "2.12.3"


val versions = new {
  val scalatest = "3.0.1"
}

libraryDependencies += "org.scalatest" %% "scalatest" % versions.scalatest % "test"