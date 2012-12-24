import sbt._
import Keys._

object $name;format="Camel,word"$Build extends sbt.Build {

  lazy val root =
    project(id = "$name;format="norm"$",
            base = file(".")) aggregate(core)

  lazy val core =
    project(id = "$name;format="norm"$-core",
            base = file("$name$-core"),
           )
            
  def project(id: String, base: File, settings: Seq[Project.Setting[_]] = Nil) =
    Project(id = id,
            base = base,
            settings = Project.defaultSettings ++ Shared.settings ++ Seq(
              libraryDependencies <++= Shared.testDeps
            ))
}

object Shared {
    
  val testDeps = Seq(
    "org.specs2" %% "specs2" % "1.12.4" % "test"
  )

  val settings = Seq(
    organization := "$organization$",
    version := "$version$",
    scalaVersion := "2.9.2",
    crossScalaVersions := Seq("2.9.1"),
    resolvers += "Scala-Tools Maven2 Snapshots Repository" at "http://scala-tools.org/repo-snapshots",
    initialCommands := "import $organization$.$name;format="lower,word"$._"
  )
  
}
