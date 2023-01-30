package com.du.sbt

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import java.io.File
import scala.io.Source

class Web3jsourcegeneratepluginTest extends AnyFlatSpec with Matchers {
  "I" should "be able to recurse files in the src/contracts dir" in {
    val f = new File(getClass.getClassLoader.getResource("./contracts").getPath)
    f.exists() should be(true)
    f.isDirectory should be(true)

    val tree = Generator.getFileTree(f)

    val files = Generator.findContracts(tree)

    files.size should be(1)

    files.head.getName should be("File.json")
  }

  it should "Replace dots with slashes e.g. convert package to path." in {
    Generator.pathToDir("com.du.test") should be("com/du/test")
  }

  it should "Rename jason to java" in {
    Generator.nameJsonToJava("File.json") should be("File.java")
  }

  it should "Uppercase first letter" in {
    Generator.nameJsonToJava("file.json") should be("File.java")
  }
}
