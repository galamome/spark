scalaVersion := "2.11.12"
//scalaVersion := "2.12.18"

name := "sparktutorial"
organization := "com.example"
version := "1.0"

// Version correspondant à 2.11.12
val sparkVersion = "2.3.2"
// Version correspondant à Scala 
//val sparkVersion = "3.5.0"

libraryDependencies ++= Seq(
	"org.apache.spark" %% "spark-core" % sparkVersion,
	"org.apache.spark" %% "spark-sql" % sparkVersion,
  	"org.apache.spark" %% "spark-hive" % sparkVersion
)

// Compatibilite sbt et Spark en local

fork := true // permet de separer le processus sbt et spark

connectInput in run := true // connecte la sortie standard a sbt pendant le run

outputStrategy := Some(StdoutOutput) // supprime les prefixes pour les logs non-sbt