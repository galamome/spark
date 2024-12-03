
# Spark dans des containers basés sur des images Bitnami

Inspiré de vidéo Youtube `Apache SPARK Up and Running FAST with Docker` :

https://github.com/bitnami/containers/blob/main/bitnami/spark/docker-compose.yml

Se connecter : http://localhost:8080 pour récupérer l'URL Spark (du type `spark://297209570e6f:7077`)

## Spark-shell

### Dans le container `master`

Rentrer dans le container Spark Master :

```bash
# Récupérer l'ID du container
docker ps | grep spark
# Rentrer dans le container
docker exec -it ${MY_CONTAINER_ID} /bin/bash
```

Dans le container

```bash
# Exécuter le Spark Shell
/opt/bitnami/spark/bin/spark-shell spark://297209570e6f:7077
```

### Exécuter des commandes simples

> NOTE : pour coller de multiples ligne d'un coup dans Spark-shell, faire `:paste`.

```scala
import spark.implicits._
import spark.sql
import org.apache.spark.sql.SparkSession

val spark = SparkSession
  .builder()
  .appName("Spark SQL basic example")
  .config("spark.some.config.option", "some-value")
  .getOrCreate()
```

> NOTE : à coller en plusieurs fois...

```scala
// Définit une classe
case class dup(name: String, value: Int)

val dupDF = spark.sparkContext
  .textFile("/test-files/dups.txt")
  .map(_.split(","))
  .map(e => dup(e(0).trim, e(1).trim.toInt))
  .toDF()

dupDF.createOrReplaceTempView("dupsTable")

dupDF.groupBy("name")
  .sum("value")
  .withColumnRenamed("name", "Name")
  .withColumnRenamed("sum(value)", "Sum")
  .show
```