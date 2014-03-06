package au.com.cba.omnia.uniform.dependency

import sbt._, Keys._

object UniformDependencyPlugin extends Plugin {
  object depend {
    object versions {
      def hadoop = "2.0.0-mr1-cdh4.3.0"
      def scalaz = "7.0.5"
      def specs = "2.2.2"
      def mockito = "1.9.0"
      def scalacheck = "1.10.1"
      def jodaTime = "2.1"
      def nscalaTime = "0.6.0"
      def scalding = "0.9.0rc4"
      def algebird = "0.3.0"
      def log4j = "1.2.17"
      def slf4j = "1.7.5"
      def scallop = "0.9.3"
      def pegdown = "1.2.1"
      def classutil = "1.0.2"
      def scrooge = "3.12.0"
      def bijection = "0.6.0"
      def libthrift = "0.8.0"
    }

    def omnia(project: String, version: String): Seq[ModuleID] =
      Seq("au.com.cba.omnia" %% project % version)

    def scaldingproject(
      hadoop: String = versions.hadoop,
      scalding: String = versions.scalding,
      algebird: String = versions.algebird,
      log4j: String = versions.log4j,
      slf4j: String = versions.slf4j,
      specs: String = versions.specs,
      mockito: String = versions.mockito,
      scalacheck: String = versions.scalacheck,
      scalaz: String = versions.scalaz,
      pegdown: String = versions.pegdown,
      classutil: String = versions.classutil
    ) =
      this.hadoop(hadoop) ++
        this.scalding(scalding, algebird) ++
        this.logging(log4j, slf4j) ++
        this.testing(specs, mockito, scalacheck, scalaz, pegdown, classutil)

    def hadoop(version: String = versions.hadoop) = Seq(
      "org.apache.hadoop"        %  "hadoop-client"                 % version        % "provided",
      "org.apache.hadoop"        %  "hadoop-core"                   % version        % "provided"
    )

    def scalaz(version: String = versions.scalaz) = Seq(
      "org.scalaz"               %% "scalaz-core"                   % version,
      "org.scalaz"               %% "scalaz-concurrent"             % version
    )

    def testing(specs: String = versions.specs, mockito: String = versions.mockito, scalacheck: String = versions.scalacheck, scalaz: String = versions.scalaz, pegdown: String = versions.pegdown, classutil: String = versions.classutil) = Seq(
      "org.specs2"               %% "specs2"                        % specs       % "test",
      "org.mockito"              %  "mockito-all"                   % mockito     % "test",
      "org.scalacheck"           %% "scalacheck"                    % scalacheck  % "test",
      "org.scalaz"               %% "scalaz-scalacheck-binding"     % scalaz      % "test",
      "org.pegdown"              %  "pegdown"                       % pegdown     % "test",
      "org.clapper"              %% "classutil"                     % classutil   % "test"
    )

    def time(joda: String = versions.jodaTime, nscala: String = versions.nscalaTime) = Seq(
      "joda-time"                %  "joda-time"                     % joda,
      "com.github.nscala-time"   %% "nscala-time"                   % nscala
    )

    def scalding(scalding: String = versions.scalding, algebird: String = versions.algebird) = Seq(
      "com.twitter"              %% "scalding-core"                 % scalding,
      "com.twitter"              %% "algebird-core"                 % algebird
    )

    def logging(log4j: String = versions.log4j, slf4j: String = versions.slf4j) = Seq(
      "log4j"                    %  "log4j"                         % log4j,
      "org.slf4j"                %  "slf4j-api"                     % slf4j,
      "org.slf4j"                %  "slf4j-log4j12"                 % slf4j
    )

    def scallop(version: String = versions.scallop) = Seq(
      "org.rogach"               %% "scallop"                       % version
    )

    def scrooge(scrooge: String = versions.scrooge, bijection: String = versions.bijection, libthrift: String = versions.libthrift) = Seq(
      "com.twitter"       %% "scrooge-core"                         % scrooge,
      "com.twitter"       %% "scrooge-runtime"                      % scrooge,
      "com.twitter"       %% "bijection-scrooge"                    % bijection,
      "org.apache.thrift" %  "libthrift"                            % libthrift
    )
  }
}
