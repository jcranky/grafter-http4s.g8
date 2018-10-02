
organization := "$organization$"
name := "$name;format="norm"$"
version := "0.1.0-SNAPSHOT"
scalaVersion := "$scala_version$"

val http4sVersion = "$http4s_version$"
val grafterVersion = "$grafter_version$"
val logbackVersion = "1.2.3"

scalacOptions ++= Seq("-Ypartial-unification")

libraryDependencies ++= Seq(
  "org.zalando"    %% "grafter"             % grafterVersion,
  "ch.qos.logback" %  "logback-classic"     % logbackVersion,
  "org.http4s"     %% "http4s-blaze-server" % http4sVersion,
  "org.http4s"     %% "http4s-circe"        % http4sVersion,
  "org.http4s"     %% "http4s-dsl"          % http4sVersion
)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
