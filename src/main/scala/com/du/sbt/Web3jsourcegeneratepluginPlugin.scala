package com.du.sbt

import sbt.*
import sbt.Keys.*
import sbt.plugins.JvmPlugin
import sbt.{AutoPlugin, Def, PluginTrigger, *}

import java.io.File

object Web3jsourcegeneratepluginPlugin extends AutoPlugin {

  override def trigger = allRequirements
  override def requires = JvmPlugin

  object autoImport {
    val contractsSetting = settingKey[File]("A setting that is automatically imported to the build")
    val outputSetting = settingKey[File]("A setting that is automatically imported to the build")
    val sourceDirSetting = settingKey[File]("A setting that is automatically imported to the build")
    val packageSetting = settingKey[String]("A setting that is automatically imported to the build")
    val codegen = taskKey[Seq[File]]("A task that is automatically imported to the build")
  }

  import autoImport._

  override lazy val projectSettings: Seq[Def.Setting[_]] =
    inConfig(Compile)(baseSettings) ++
      inConfig(Test)(baseSettings)

  lazy val baseSettings: Seq[Def.Setting[_]] = Seq(
    contractsSetting := (sourceDirectory.value / "contracts"),
    outputSetting := sourceManaged.value, /// "compiled_abi" ,
    codegen := {
      println("Bob")
      sLog.value.info("Generating sources for " + contractsSetting.value.getPath)
      sLog.value.info("Package " + packageSetting.value)
      Generator(contractsSetting.value, outputSetting.value, packageSetting.value).generateSources()
    }
  )

}
