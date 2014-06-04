uniform
=======

[![Build Status](https://magnum.travis-ci.com/CommBank/uniform.svg?token=A3xq7fpHLyey1yCrNASy&branch=master)](https://magnum.travis-ci.com/CommBank/uniform)
[![Gitter chat](https://badges.gitter.im/CommBank.png)](https://gitter.im/CommBank)

```
uniform: remaining the same in all cases and at all times; unchanging in form or character.
```

An sbt plugin for maintaining uniform approach to building cba components.

usage
-----

`uniform` helps provide a consistent set of scala settings across projects.

To depend on it, add an entry to `project/plugins.sbt` with an appropriate repository configures:

```
resolvers += Resolver.url("commbank-releases-ivy", new URL("http://commbank.artifactoryonline.com/commbank/ext-releases-local-ivy"))(Patterns("[organization]/[module]_[scalaVersion]_[sbtVersion]/[revision]/[artifact](-[classifier])-[revision].[ext]"))

addSbtPlugin("au.com.cba.omnia" % "uniform-core" % "$VERSION$")
```

Almost all functionality, is included by using the `uniform.project` command.

```
  uniform.project("project-name", "root.package")
```

A concrete example might be:
```
  uniform.project("thermometer", "au.com.cba.omnia.thermometer")
```

The only additional thing to include is the unique version number plugin for
generating sane versioned components as opposed to snapshots and their ilk.

Versioning should be done in a `version.sbt` file in the root of a project, and
it should look like this:

```
version in ThisBuild := "0.0.1"

uniqueVersionSettings
```

This will generate a unique build, consisiting of `${version}-${timestamp}-${commish}`.


`uniform` flavours provide additional pre-canned configs for `assembly`, `thrift` and consistent dependency versions.

a complete example
------------------

`project/plugins.sbt`

```
resolvers += Resolver.url("commbank-releases-ivy", new URL("http://commbank.artifactoryonline.com/commbank/ext-releases-local-ivy"))(Patterns("[organization]/[module]_[scalaVersion]_[sbtVersion]/[revision]/[artifact](-[classifier])-[revision].[ext]"))

addSbtPlugin("au.com.cba.omnia" % "uniform-core" % "$VERSION$")

addSbtPlugin("au.com.cba.omnia" % "uniform-thrift" % "$VERSION$")

addSbtPlugin("au.com.cba.omnia" % "uniform-assembly" % "$VERSION$")

addSbtPlugin("au.com.cba.omnia" % "uniform-dependency" % "$VERSION$")
```

`build.sbt`

```
uniform.project("project-name", "root.package")

uniformThriftSettings

uniformAssemblySettings
```

`version.sbt`

```
version in ThisBuild := "0.0.1"

uniqueVersionSettings
```
