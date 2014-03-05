package au.com.cba.omnia.uniform
package standard

import setting.ScalaSettings.scala
import version.VersionInfoPlugin.{versionInfoSettings, rootPackage}

import sbt._, Keys._

object StandardProjectPlugin extends Plugin {
  object uniform {
    def project(project: String, pkg: String) = List(
      name := project,
      organization := "au.com.cba.omnia",
      rootPackage := pkg
    ) ++ scala.settings
  }
}
