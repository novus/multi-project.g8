import sbt._
import Keys._
import sbtrelease.ReleasePlugin._

object Build extends sbt.Build {

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
            settings =
              Project.defaultSettings ++
              Shared.settings ++
              releaseSettings ++
              settings ++
              Seq(
                libraryDependencies ++= Shared.testDeps
              )).settings(Defaults.itSettings:_*).configs(IntegrationTest)
}
