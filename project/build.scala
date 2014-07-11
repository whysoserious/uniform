//   Copyright 2014 Commonwealth Bank of Australia
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

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
      , "-target:jvm-1.6"
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
    , addSbtPlugin("com.typesafe.sbt" % "sbt-site" % "0.8.1")
    , addSbtPlugin("com.eed3si9n" % "sbt-unidoc" % "0.3.1")
    )
  )

  lazy val thrift = Project(
    id = "thrift"
  , base = file("uniform-thrift")
  , settings = standardSettings ++ Seq[Sett](
      name := "uniform-thrift"
    , resolvers += Resolver.sonatypeRepo("releases")
    , addSbtPlugin("com.twitter" %% "scrooge-sbt-plugin" % "3.14.1")
    )
  ).dependsOn(dependency)

  lazy val assembly = Project(
    id = "assembly"
  , base = file("uniform-assembly")
  , settings = standardSettings ++ Seq[Sett](
      name := "uniform-assembly"
    , addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.11.2")
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
