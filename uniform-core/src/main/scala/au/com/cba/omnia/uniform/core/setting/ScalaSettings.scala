package au.com.cba.omnia.uniform.core
package setting

import sbt._
import Keys._

object ScalaSettings extends Plugin {
  object scala {
    def settings = Seq(
      scalaVersion := "2.10.3",
      crossScalaVersions := Seq("2.10.3"),
      scalacOptions ++= Seq(
        "-deprecation",
        "-unchecked",
        "-Ywarn-all",
        "-Xlint",
        "-feature",
        "-language:_",
        "-target:jvm-1.6"
      ),
      javacOptions ++= Seq(
        "-source", "1.6",
        "-target", "1.6"
      )
    )
  }
}
