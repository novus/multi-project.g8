resolvers ++= Seq(
  "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
  "Novus Nexus Public" at "https://nexus.novus.local:65443/nexus/content/groups/public/",
  "Novus Snpashots" at "https://nexus.novus.local:65443/nexus/content/repositories/snapshots"
)

credentials += Credentials(Path.userHome / ".ivy2" / ".novus_nexus")

addSbtPlugin("com.typesafe.sbtscalariform" % "sbtscalariform" % "0.4.0")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.1.0")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.0")