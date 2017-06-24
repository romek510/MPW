name := """MyDropbox"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "mysql" % "mysql-connector-java" % "5.1.38",
  "commons-net" % "commons-net" % "3.6",
  "commons-io" % "commons-io" % "2.5"


)
