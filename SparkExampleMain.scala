package com.example.sparktutorial

import org.apache.spark.sql.SparkSession
import org.apache.spark._ // liste des imports
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

import com.example.sparktutorial.Analysis.calculateAverageTipByPickupLocation

object RepartitionVsCoalesceExample extends App {
  val conf = new SparkConf()

  val spark = SparkSession.builder()
    .appName("RepartitionVsCoalesceExample")
    .master("local[5]")
    .config(conf)
    .getOrCreate()

  val df = spark.range(0, 20)

  println("Nombre initial de partitions : " + df.rdd.getNumPartitions)

  // Appelle une fonction d'affichage des partitions et de leur contenu
  val dfPartitionsMappees = df.rdd.mapPartitionsWithIndex(printDataInPartition)
  // Le ".count()" sert uniquement à ce que la fonction d'affichage soit exécutée
  dfPartitionsMappees.count()

  // Force la répartition
  val df2 = df.repartition(6)

  println("Nombre de partitions après forçage par fonction 'repartition(6)': " + df2.rdd.getNumPartitions)
  // Appelle une fonction d'affichage des partitions et de leur contenu
  val df2PartitionsMappees = df2.rdd.mapPartitionsWithIndex(printDataInPartition)
  // Le ".count()" sert uniquement à ce que la fonction d'affichage soit exécutée
  df2PartitionsMappees.count()

  // Coalesce
  val df3 = df.coalesce(2)

  println("Nombre de partitions après 'coalesce(2)': " + df3.rdd.getNumPartitions)
  // Appelle une fonction d'affichage des partitions et de leur contenu
  val df3PartitionsMappees = df3.rdd.mapPartitionsWithIndex(printDataInPartition)
  // Le ".count()" sert uniquement à ce que la fonction d'affichage soit exécutée
  df3PartitionsMappees.count()

  // Arrête la session Spark
  spark.stop()

  /**
   * Définit une fonction pour montrer le contenu de chaque partition
   */
  private def printDataInPartition(index: Int, iterator: Iterator[java.lang.Long]): Iterator[Unit] = {
    val partitionData = iterator.toList
    val i = index + 1
    println(s"Partition $i: ${partitionData.mkString(", ")}")
    Iterator.empty
  }
}
