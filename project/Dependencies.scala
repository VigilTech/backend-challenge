import sbt._

/**
 *  Contains all dependencies for the project
 */
object Dependencies {
  import Libraries._

  val AllLibraries: Seq[ModuleID] = Seq(
    ScalaTest,
    Scopt,
    ZioHttp,
  )

  object Versions {
    val ScalaTest = "3.1.4"
    val Scopt = "4.1.0"
    val ZioHttp = "3.0.0-RC2"
  }

  object Libraries {
    val ScalaTest = "org.scalatest" %% "scalatest" % Versions.ScalaTest % Test
    val Scopt = "com.github.scopt" %% "scopt" % Versions.Scopt
    val ZioHttp = "dev.zio" %% "zio-http" % Versions.ZioHttp

  }

}
