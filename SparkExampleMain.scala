package com.example.sparktutorial

import org.apache.spark.sql.SparkSession
import org.apache.spark._  // liste des imports
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

import com.example.sparktutorial.Analysis.calculateAverageTipByPickupLocation

object SparkExampleMain extends App {

/*
    val spark = SparkSession.builder()
        .master("local[5]")
        .appName("Toto")
        .getOrCreate()
    
    val df = spark.range(0,20)
    df.show
    */
     override def main(args: Array[String]) { // definition de la fonction main

      val conf = new SparkConf()

      val spark = SparkSession.builder()
         .appName("SparkApp")
         .master("local[5]")
         .enableHiveSupport()
         .config(conf)
         .getOrCreate()

      import spark.implicits._   // fonctions utilitaires supplementaires

      spark.sparkContext.setLogLevel("ERROR") // diminuer la verbosite

      var test_df = Seq(
      (1, 91, "chat"),
      (2, 92, null),
      (3, 93, "souris")
      ).toDF("id", "valeur","animal")

      test_df.show

      test_df.where($"animal" === "souris").show

      spark.sparkContext.setLogLevel("INFO")

      spark.stop()
   }
}