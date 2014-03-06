package au.com.cba.omnia.uniform.assembly

import sbt._, Keys._
import sbtassembly.Plugin._, AssemblyKeys._

object UniformAssemblyPlugin extends Plugin {
  def uniformAssemblySettings: Seq[Sett] =
    assemblySettings ++ Seq[Sett](
      mergeStrategy in assembly <<= (mergeStrategy in assembly)(defaultMergeStrategy),
      test in assembly := {},
      (artifact in (Compile, assembly) ~= { art =>
        art.copy(`classifier` = Some("assembly"))
      })
    ) ++  addArtifact(artifact in (Compile, assembly), assembly)

  def defaultMergeStrategy(old: String => MergeStrategy) =  (path: String) => path match {
    case "META-INF/NOTICE.txt" => MergeStrategy.rename
    case "META-INF/LICENSE.txt" => MergeStrategy.rename
    case "META-INF/MANIFEST.MF" => MergeStrategy.discard
    case PathList("META-INF", xs) if xs.toLowerCase.endsWith(".dsa") => MergeStrategy.discard
    case PathList("META-INF", xs) if xs.toLowerCase.endsWith(".rsa") => MergeStrategy.discard
    case PathList("META-INF", xs) if xs.toLowerCase.endsWith(".sf") => MergeStrategy.discard
    case _ => MergeStrategy.first
  }

}
