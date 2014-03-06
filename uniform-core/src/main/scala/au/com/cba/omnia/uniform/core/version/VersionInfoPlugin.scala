package au.com.cba.omnia.uniform.core
package version

import sbt._, Keys._

import java.util.Date
import java.text.SimpleDateFormat

object VersionInfoPlugin extends Plugin {
  import Times._
  import GitInfo._

  lazy val rootPackage = SettingKey[String]("root-package")

  lazy val versionInfoSettings: Seq[Sett] = Seq(
    (sourceGenerators in Compile) <+= (sourceManaged in Compile, target, name, version, baseDirectory, rootPackage).map((src, target, name, version, base, pkg) => {
      val scala = src / "info.scala"
      val txt = target / "VERSION.txt"
      IO.write(scala, """package %s
                       |object VersionInfo {
                       |  val version = "%s"
                       |  val git = "%s"
                       |  val date = "%s"
                       |}""".stripMargin.format(pkg, version, commit(base), timestamp(now)))
      IO.write(txt, """VERSION=%s
                       |GIT=%s
                       |DATE=%s""".stripMargin.format(version, commit(base), timestamp(now)))
      Seq(scala, txt)
    })
  )
}
