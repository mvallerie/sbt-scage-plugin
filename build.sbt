import bintray.Keys._

scalacOptions += "-deprecation"

lazy val commonSettings = Seq(
  version in ThisBuild := "0.1",
  organization in ThisBuild := "com.github.mvallerie"
)

lazy val root = (project in file(".")).
  settings(commonSettings ++ bintrayPublishSettings: _*).
  settings(
    sbtPlugin := true,
    name := "sbt-scage-plugin",
    description := "A simple sbt plugin for Scage, a scala game engine",
    licenses += ("GPL-3.0", url("http://www.gnu.org/licenses/gpl-3.0.html")),
    publishMavenStyle := false,
    repository in bintray := "sbt-plugins",
    bintrayOrganization in bintray := None
  )
