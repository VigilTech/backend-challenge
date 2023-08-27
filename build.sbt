ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / organization := "com.interview.task"
ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("."))
  .settings(
    name := "backend-challenge",
    assembly / assemblyJarName := s"${name.value}-${version.value}.jar",
    libraryDependencies ++= Dependencies.AllLibraries,
  )

ThisBuild / assembly / assemblyMergeStrategy := {
  case "META-INF/io.netty.versions.properties" => MergeStrategy.concat
  case x                                       => MergeStrategy.defaultMergeStrategy(x)
}
