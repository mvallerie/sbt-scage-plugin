sbtPlugin := true

name := "sbt-scage-plugin"

version := "0.1-SNAPSHOT"

publishTo := {
   val scalasbt = "http://repo.scala-sbt.org/scalasbt/"
   val (name, url) = if (version.value.contains("-SNAPSHOT"))
     ("sbt-plugin-snapshots", scalasbt+"sbt-plugin-snapshots")
   else
     ("sbt-plugin-releases", scalasbt+"sbt-plugin-releases")
   Some(Resolver.url(name, new URL(url))(Resolver.ivyStylePatterns))
}

publishMavenStyle := false
