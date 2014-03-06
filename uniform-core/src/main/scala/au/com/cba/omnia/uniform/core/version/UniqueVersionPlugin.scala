package au.com.cba.omnia.uniform.core
package version

import sbt._, Keys._

object UniqueVersionPlugin extends Plugin {
  import Times._
  import GitInfo._

  def uniqueVersionSettings = Seq(
    version in ThisBuild        <<=  (version in ThisBuild, baseDirectory).apply((v, d)=> v + "-" + timestamp(now) + "-" + commish(d))
  )
}
