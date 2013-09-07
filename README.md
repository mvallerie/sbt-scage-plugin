sbt-scage-plugin
================

This is a simple SBT plugin designed to integrate Scage (Scala Game Engine) to your projects.

How to use
----------

[Scage](https://www.github.com/dunnololda/scage/) is a game engine written in Scala. To integrate it to your SBT projects, read the following :

Currently the plugin is not deployed in SBT plugins repository (I plan to do it later), you have to get the sources and compile it :

###
	$ git clone https://github.com/mvallerie/sbt-scage-plugin.git
	$ cd sbt-scage-plugin
	$ sbt publish-local
###

Okay ! Now let's configure your new SBT/Scage project :

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

Please note that you might have to run "update" task twice to get some problematic dependencies (mainly phys2d), this is a normal behaviour.

Example
-------

You can check out the example directory to get a quick start. I'll release a template when i'll get more time :).
