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
    "org.specs2" %% "specs2" % "1.12.3" % "test",
    "org.mockito" % "mockito-all" % "1.9.0" % "test",
    "org.scalacheck" %% "scalacheck" % "1.9" % "test",
    "junit" % "junit" % "4.7" % "test"
  )

  val settings = Seq(
    organization := "$organization$",
    version := "$version$",
    scalaVersion := "2.9.2",
    crossScalaVersions := Seq("2.9.1"),
    scalacOptions := Seq("-deprecation", "-unchecked"),
    javacOptions ++= Seq("-source", "1.5", "-target", "1.5"),
    testOptions in Test += Tests.Argument("console", "junitxml"),
    resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                      "releases"  at "http://oss.sonatype.org/content/repositories/releases"),
    initialCommands := "import $organization$.$name;format="lower,word"$._",
    shellPrompt := ShellPrompt.buildShellPrompt
  ) ++ net.virtualvoid.sbt.graph.Plugin.graphSettings
  
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
      "[%s](%s)\$ ".format (
        currProject, currBranch /*, BuildSettings.buildVersion*/
      )
    }
  }
}
