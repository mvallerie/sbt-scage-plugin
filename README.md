sbt-scage-plugin
================

This is a simple SBT plugin designed to integrate Scage (Scala Game Engine) to your projects.

How to use
----------

[Scage](https://www.github.com/dunnololda/scage/) is a game engine written in Scala. To integrate it to your SBT projects, do the following :

###project/plugins.sbt
	addSbtPlugin("sbt-scage-plugin" % "sbt-scage-plugin" % "0.1-SNAPSHOT")
###

###build.sbt
	name := "foobar"

	version := "0.1-SNAPSHOT"

	scalaVersion := "2.10.2"

	seq(scageSettings: _*)
###

You can change the scalaVersion but you may have issues if you put an older version than Scage's (currently 2.10.1).

You can also change Scage version using :

###build.sbt
	scage.version := "0.9.3-SNAPSHOT"
###

By default, the most recent version is selected (the plugin will be updated for each version of Scage).

Example
-------

You can check out the example directory to get a quick start. I'll release a template when i'll get more time :).
