resolvers += Resolver.url("sbt-plugin-snapshots", new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots/"))(Resolver.ivyStylePatterns)

addSbtPlugin("com.github.mvallerie" % "sbt-scage-plugin" % "0.1-SNAPSHOT")
