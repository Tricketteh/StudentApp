name := """StudentApp"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.16"

libraryDependencies ++= Seq(
  guice,
  "org.mongodb.scala" %% "mongo-scala-driver" % "5.3.1",
  "org.mongodb.scala" %% "mongo-scala-bson" % "5.3.1",
  "com.typesafe.play" %% "play-json" % "2.10.6",
  "com.typesafe.play" %% "play-slick" % "5.3.1",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.3.1",
  "com.github.t3hnar" %% "scala-bcrypt" % "4.3.0",
  "com.pauldijou" %% "jwt-play" % "5.0.0",
  "org.pac4j" %% "play-pac4j" % "11.1.0-PLAY2.8"
)