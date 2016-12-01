/*
 * Copyright 2016 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import sbt.Keys._
import sbt._
import uk.gov.hmrc.versioning.SbtGitVersioning

object HmrcBuild extends Build {

  import uk.gov.hmrc._

  val appName = "play-random-json-filter"

  lazy val library: Project = Project(appName, file("."))
    .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning)
    .settings(
      scalaVersion := "2.11.8",
      libraryDependencies ++= AppDependencies(),
      crossScalaVersions := Seq("2.11.8"),
      parallelExecution in Test := false,
      resolvers := Seq(
        Resolver.bintrayRepo("hmrc", "releases"), "typesafe-releases" at "http://repo.typesafe.com/typesafe/releases/")
    )
}

private object AppDependencies {

  // Important note: Play is *not* a dependency here, nor is it a transitive dependency.

  private val jacksonVersion = "2.7.4"

  val compile = Seq(
    "uk.gov.hmrc" %% "logging" % "0.2.0" withSources(),
    "uk.gov.hmrc" %% "play-filters" % "5.6.0" withSources(),
    "com.typesafe.play" %% "play" % "2.5.8"
  )

  trait TestDependencies {
    lazy val scope: String = "test"
    val test: Seq[ModuleID]
  }

  object Test {
    def apply(): Seq[ModuleID] = new TestDependencies {
      override lazy val test = Seq(
        "org.scalatest" %% "scalatest" % "3.0.1" % scope,
        "org.scalacheck" %% "scalacheck" % "1.13.4" % scope,
        "org.pegdown" % "pegdown" % "1.6.0" % scope,
        "org.mockito" % "mockito-all" % "1.10.19" % scope,
        "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % scope
      )
    }.test
  }

  def apply(): Seq[ModuleID] = compile ++ Test()
}
