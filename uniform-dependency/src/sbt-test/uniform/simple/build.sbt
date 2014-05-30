scala.settings

uniformDependencySettings

version := "0.1"

libraryDependencies ++= depend.scaldingproject() ++ depend.scalaz() ++ depend.testing() ++
                        depend.time() ++ depend.scallop() ++ depend.scrooge()
