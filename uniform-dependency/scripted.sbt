ScriptedPlugin.scriptedSettings


scriptedLaunchOpts := {
  scriptedLaunchOpts.value ++
  Seq("-Xmx1024M", "-XX:MaxPermSize=256M", "-Dplugin.version=" + version.value)
}

scriptedBufferLog := false

scriptedRun <<= {
  scriptedRun.dependsOn((publishLocal in core))
}

credentials ++= {
  val out = credentials.value.map {
    case c: FileCredentials   => s"""Credentials(new java.io.File("${c.path.getAbsolutePath}"))"""
    case c: DirectCredentials => s"Credentials(${c.realm}, ${c.host}, ${c.userName}, ${c.passwd})"
  }.mkString(" credentials ++= Seq(", ",", ")")
  sbtTestDirectory.value.listFiles.flatMap(_.listFiles).map(f => IO.writeLines(f / "credentials.sbt", Seq(out)))
  credentials.value
}
