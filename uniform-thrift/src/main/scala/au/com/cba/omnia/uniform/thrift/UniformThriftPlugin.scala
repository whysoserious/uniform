package au.com.cba.omnia.uniform.thrift

import au.com.cba.omnia.uniform.dependency.UniformDependencyPlugin._
import com.twitter.scrooge.ScroogeSBT._
import sbt._, Keys._

object UniformThriftPlugin extends Plugin {
  def uniformThriftSettings: Seq[Sett] = newSettings ++ Seq[Sett](
    libraryDependencies ++= depend.scrooge(),
    // Force scrooge-gen to always be run (it is buggy w.r.t. picking up changes to new thrift files).
    (scroogeIsDirty in Compile) <<= (scroogeIsDirty in Compile) map { (_) => true }
  )
}
