name := "http4s"

version := "1.0"

scalaVersion := "2.13.5"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-feature",
  "-unchecked",
  "-language:reflectiveCalls",
  "-Ywarn-numeric-widen",
  "-target:11",
  "-Xlint"
)

enablePlugins(SbtTwirl)

//val http4sVersion = "0.21-20-ac0d321-SNAPSHOT" // both locks contention fixed and fs2 hack
//val http4sVersion = "0.21-20-5f27116-SNAPSHOT" //fs2hack
//val http4sVersion = "0.21-21-49d3ede-SNAPSHOT" //fs2alsohack
//val http4sVersion = "0.21-20-9268e8e-SNAPSHOT" // thread shift avoided
//val http4sVersion = "0.21-22-7f2129e-SNAPSHOT" // both locks contention fixed + thread shift avoided
//val http4sVersion = "0.21-21-854938a-SNAPSHOT" // both locks contention fixed
//val http4sVersion = "0.21-19-cf04ebf-SNAPSHOT" // baseline 2 (cf04ebf0dd6b5890dfec1ca19964b9b2b23dfab1)
//val http4sVersion = "0.21.22"

val http4sVersion = "0.22-96-878e844-SNAPSHOT" // this is 1.0.0


assembly / assemblyMergeStrategy := {
  case PathList(xs @ _*) if xs.last == "io.netty.versions.properties" => MergeStrategy.rename
  case other => (assembly / assemblyMergeStrategy).value(other)
}

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-twirl" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion,
  // Optional for auto-derivation of JSON codecs
  "io.circe" %% "circe-generic" % "0.14.0-M5",
  "org.typelevel" %% "cats-effect" % "3.1.0",
//  "co.fs2" %% "fs2-core" % "2.5.4",
//  "co.fs2" %% "fs2-io" % "2.5.4",
  "io.getquill" %% "quill-jasync-postgres" % "3.7.0",
  "io.getquill" %% "quill-jasync" % "3.7.0",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)

addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
