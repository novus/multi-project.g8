import sbt._
import Keys._

object $name;format="Camel,word"$Build extends sbt.Build {

  lazy val root =
    project(id = "$name;format="norm"$",
            base = file(".")) aggregate(core)

  lazy val core =
    project(id = "$name;format="norm"$-core",
            base = file("$name$-core")
           )
            
  def project(id: String, base: File, settings: Seq[Project.Setting[_]] = Nil) =
    Project(id = id,
            base = base,
            settings = Project.defaultSettings ++ Shared.settings ++ Seq(
              libraryDependencies ++= Shared.testDeps
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
    initialCommands := "import $organization$.$name;format="lower,word"$._",
    shellPrompt := ShellPrompt.buildShellPrompt
  )
  
}

// Shell prompt which show the current project, git branch and build version
object ShellPrompt {
  object devnull extends ProcessLogger {
    def info (s: => String) {}
    def error (s: => String) { }
    def buffer[T] (f: => T): T = f
  }
  def currBranch = (
    ("git status -sb" lines_! devnull headOption)
      getOrElse "-" stripPrefix "## "
    )

  val buildShellPrompt = {
    (state: State) => {
      val currProject = Project.extract (state).currentProject.id
      "[%s](%s)\\$ ".format (
        currProject, currBranch /*, BuildSettings.buildVersion*/
      )
    }
  }
}
