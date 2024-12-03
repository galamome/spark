package com.example.sparktutorial

import org.apache.spark.sql.SparkSession

import com.example.sparktutorial.Analysis.calculateAverageTipByPickupLocation

object SparkExampleMain extends App {

    val spark = createSparkSession("Spark test", isLocal = true)

    val (inputPath, outputPath) = parseArgs(args = args)
    val data = spark.read.parquet(s"${inputPath}/*.parquet")

    val analysisResult = calculateAverageTipByPickupLocation(data = data)

    analysisResult.write.option("header", "true").csv(outputPath)
}
