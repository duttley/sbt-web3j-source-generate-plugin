package com.du.sbt

import com.du.sbt.Generator._
import org.web3j.codegen.TruffleJsonFunctionWrapperGenerator
import sbt.Keys.*
import sbt.Project

import java.io.File
import sbt.{AutoPlugin, Def, PluginTrigger, *}

import scala.io.Source

object Generator {
  def apply(json: File, dest: File, pack: String): Generator = new Generator(json, dest, pack)

  def getFileTree(f: File): Seq[File] =
    f #:: (if (f.isDirectory) f.listFiles().toStream.flatMap(getFileTree)
    else Stream.empty)

  def findContracts(files: Seq[File]): Seq[File] = files.filterNot(_.getName.endsWith("dbg.json")).filter(_.getName.endsWith(".json"))

  def pathToDir(path: String): String = path.replaceAll("[.]", "/")

  def nameJsonToJava(json: String): String = json.replaceAll("[.]json", ".java")
}

class Generator(json: File, dest: File, pack: String) {
  def generateSources(): Seq[File] = {
    val contracts = findContracts(getFileTree(json))
    val packageDir = new File(pathToDir(pack))
    val sourceFiles = contracts.map { c =>
      new TruffleJsonFunctionWrapperGenerator(c.getPath, dest.getPath, pack, true).generate()
      val sourceFile = new File(packageDir, c.getName)
      new File(dest, s"${pathToDir(pack)}/${nameJsonToJava(c.getName)}")
    }
    sourceFiles
  }
}

