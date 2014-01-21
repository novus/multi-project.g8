import sbt._
import Keys._

object Shared {

  val testDeps = Seq(
    "org.scalatest" %% "scalatest" % "1.9.2" % "it,test",
    "org.scalacheck" %% "scalacheck" % "1.10.1" % "it,test"
  )

  val settings = Seq(
    organization := "com.novus",
    scalaVersion := "2.10.3",
    crossScalaVersions := Seq("2.9.3", "2.10.3"),
    scalacOptions := Seq("-deprecation", "-unchecked"),
    resolvers ++= Seq("Novus Nexus Public" at "https://nexus.novus.com:65443/nexus/content/groups/public/"),
    //initialCommands := "import net.godcode.phi._",
    shellPrompt := ShellPrompt.buildShellPrompt,
    publishTo <<= (version) { version: String =>
      val sfx =
        if (version.trim.endsWith("SNAPSHOT")) "snapshots"
        else "releases"
      val nexus = "https://nexus.novus.com:65443/nexus/content/repositories/"
      Some("Novus " + sfx at nexus + sfx + "/")
    },
    credentials += Credentials(Path.userHome / ".ivy2" / ".novus_nexus")
  ) ++ net.virtualvoid.sbt.graph.Plugin.graphSettings ++ Format.settings

}
