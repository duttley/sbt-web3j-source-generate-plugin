# sbt-web3j-source-generate-plugin

An sbt AutoPlugin

## Usage

This plugin requires sbt 1.0.0+

Add the following line and config to your sbt plugins.

```scala
addSbtPlugin("io.github.duttley" % "sbt-web3j-source-generate-plugin" % "0.1.2")
```

This plugin scans src/main/contracts for any truffle files. 

The only required setting is the package setting. Add this to your project definition in build.sbt.

```scala
packageSetting := "<your package>"
```

### Testing

Run `test` for regular unit tests.

Run `scripted` for [sbt script tests](http://www.scala-sbt.org/1.x/docs/Testing-sbt-plugins.html).

### CI

The generated project uses [sbt-github-actions](https://github.com/djspiewak/sbt-github-actions) as a plugin to generate workflows for GitHub actions. For full details of how to use it [read this](https://github.com/djspiewak/sbt-github-actions/blob/main/README.md)

### Publishing

1. publish your source to GitHub
2. Follow the instructions in [sbt-ci-release](https://github.com/olafurpg/sbt-ci-release/blob/main/readme.md) to create a sonatype account and setup your keys
3. `sbt ci-release`
4. [Add your plugin to the community plugins list](https://github.com/sbt/website#attention-plugin-authors)
5. [Claim your project an Scaladex](https://github.com/scalacenter/scaladex-contrib#claim-your-project)
