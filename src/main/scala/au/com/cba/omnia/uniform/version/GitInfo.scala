package au.com.cba.omnia.uniform
package version

import java.io.File

import sbt._, Keys._

object GitInfo {
  def commish(root: File) =
    gitlog(root, "%h")

  def commit(root: File) =
    gitlog(root, "%H")

  def gitlog(root: File, format: String) =
    Process("git log --pretty=format:" + format + " -n  1", Some(root)).lines.head
}
