package com.example.sparktutorial

import org.apache.spark.sql.SparkSession
import org.apache.spark._  // liste des imports
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

import com.example.sparktutorial.Analysis.calculateAverageTipByPickupLocation

// https://sparktpoint.com/spark-repartition-vs-coalesce/
object RepartitionVsCoalesceExample extends App {

     override def main(args: Array[String]) { // definition de la fonction main

      val conf = new SparkConf()

      val spark = SparkSession.builder()
         .appName("RepartitionVsCoalesceExample")
         .master("local[5]")
         .enableHiveSupport()
         .config(conf)
         .getOrCreate()

      import spark.implicits._   // fonctions utilitaires supplementaires

    val df = spark.range(0,20)
    println("Nombre initial de partitions : " + df.rdd.getNumPartitions)
    df.rdd.mapPartitionsWithIndex(printDataInPartition).count()
      spark.stop()
   }

  // Définit une fonction pour montrer le contenu de chaque partition
  def printDataInPartition(index: Int, iterator: Iterator[java.lang.Long]): Iterator[Unit] = {
    val partitionData = iterator.toList
    val i  = index +1
    println(s"Partition $i: ${partitionData.mkString(", ")}")
    Iterator.empty
  }
}
