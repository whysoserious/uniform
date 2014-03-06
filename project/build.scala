import sbt._
import Keys._

object build extends Build {
  type Sett = sbt.Def.Setting[_]

  lazy val standardSettings: Seq[Sett] =
    Defaults.defaultSettings ++ Seq[Sett](
      organization := "au.com.cba.omnia"
    , sbtPlugin := true
    , scalaVersion := "2.10.3"
    , scalacOptions := Seq(
        "-deprecation"
      , "-unchecked"
      , "-optimise"
      , "-Ywarn-all"
      , "-Xlint"
      , "-Xfatal-warnings"
      , "-feature"
      , "-language:_"
      )
    )

  lazy val uniform = Project(
    id = "uniform"
  , base = file(".")
  , settings = standardSettings ++ Seq[Sett](
      name := "uniform"
    , publishArtifact := false
    )
  , aggregate = Seq(core, thrift, assembly, dependency)
  )

  lazy val core = Project(
    id = "core"
  , base = file("uniform-core")
  , settings = standardSettings ++ Seq[Sett](
      name := "uniform-core"
    )
  )

  lazy val thrift = Project(
    id = "thrift"
  , base = file("uniform-thrift")
  , settings = standardSettings ++ Seq[Sett](
      name := "uniform-thrift"
    , resolvers += Resolver.sonatypeRepo("releases")
//    , addSbtPlugin("com.twitter" %% "scrooge-sbt-plugin" % "3.12.0")
    )
  )

  lazy val assembly = Project(
    id = "assembly"
  , base = file("uniform-assembly")
  , settings = standardSettings ++ Seq[Sett](
      name := "uniform-assembly"
    , addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.10.2")
    )
  )

  lazy val dependency = Project(
    id = "dependency"
  , base = file("uniform-dependency")
  , settings = standardSettings ++ Seq[Sett](
      name := "uniform-dependency"
    )
  )
}
