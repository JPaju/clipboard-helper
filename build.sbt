Global / onChangedBuildSource := ReloadOnSourceChanges
watchBeforeCommand            := Watch.clearScreen

name         := "clipboard-helper"
version      := "0.1.0-SNAPSHOT"
scalaVersion := "3.2.1"

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-explain",
  "-Ycheck-all-patmat",
  "-Ykind-projector",
  "-Ysafe-init"
) ++ Seq("-source", "future")

val zioVersion = "2.0.5"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % zioVersion
)

libraryDependencies ++= Seq(
  "dev.zio" %% "zio-test"     % zioVersion,
  "dev.zio" %% "zio-test-sbt" % zioVersion
).map(_ % Test)
