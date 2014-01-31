name := "aws_ProductAdvertisingApi"

version := "0.9.0"

scalaVersion := "2.10.1"

libraryDependencies += "commons-codec" % "commons-codec" % "1.6"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.0" % "test"

libraryDependencies += "com.typesafe" % "config" % "1.2.0"

unmanagedClasspath in Runtime <+= (baseDirectory) map { bd => Attributed.blank(bd / ".") }
