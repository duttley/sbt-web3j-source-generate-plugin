
name := """sbt-web3j-source-generate-plugin"""
organization := "io.github.duttley"
//version := "0.1-SNAPSHOT"
//scalaVersion := "2.13.7"
sbtPlugin := true

//sonatypeCredentialHost := "s01.oss.sonatype.org"
//sonatypeRepository := "https://s01.oss.sonatype.org/service/local"

libraryDependencies ++= Seq(
  "org.web3j" % "core" % "4.8.7",
  "org.web3j" % "abi" % "4.8.7",
  "org.web3j" % "codegen" % "4.8.7",
  "org.scalactic" %% "scalactic" % "3.2.9" % "test",
  "org.scalatest" %% "scalatest" % "3.2.9" % "test"
)

inThisBuild(List(
  organization := "io.github.duttley",
  homepage := Some(url("https://github.com/duttley/sbt-web3j-source-generate-plugin")),
  licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
  developers := List(
    Developer(
      "duttley",
      "David Uttley",
      "foo@bar.org",
      url("https://github.com/duttley/")
    )
  )
))

initialCommands in console := """import io.github.duttley.sbt._"""

enablePlugins(ScriptedPlugin)
// set up 'scripted; sbt plugin for testing sbt plugins
scriptedLaunchOpts ++=
  Seq("-Xmx1024M", "-Dplugin.version=" + version.value)

ThisBuild / githubWorkflowTargetTags ++= Seq("v*")
ThisBuild / githubWorkflowPublishTargetBranches :=
  Seq(RefPredicate.StartsWith(Ref.Tag("v")))

ThisBuild / githubWorkflowPublish := Seq(
  WorkflowStep.Sbt(
    List("ci-release"),
    env = Map(
      "PGP_PASSPHRASE" -> "${{ secrets.PGP_PASSPHRASE }}",
      "PGP_SECRET" -> "${{ secrets.PGP_SECRET }}",
      "SONATYPE_PASSWORD" -> "${{ secrets.SONATYPE_PASSWORD }}",
      "SONATYPE_USERNAME" -> "${{ secrets.SONATYPE_USERNAME }}"
    )
  )
)

