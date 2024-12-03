scalaVersion := "2.11.12"
//scalaVersion := "2.12.18"

name := "sparktutorial"
organization := "com.example"
version := "1.0"

// Version correspondant à 2.11.12
val sparkVersion = "2.4.7"
// Version correspondant à Scala 
//val sparkVersion = "3.5.0"

libraryDependencies ++= Seq(
	"org.apache.spark" %% "spark-core" % sparkVersion,
	"org.apache.spark" %% "spark-sql" % sparkVersion
)

fork := true
