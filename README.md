uniform
=======

``
uniform: remaining the same in all cases and at all times; unchanging in form or character.
```

An sbt plugin for maintaining uniform approach to building cba components.

usage
-----

`uniform` helps provide a consistent set of scala settings across projects.

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
