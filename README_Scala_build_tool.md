

Tirer l'image Docker contenant SBT : https://hub.docker.com/r/sbtscala/scala-sbt/tags

```bash
# Déclaration des variables :
# Version de JVM
SBT_VERSION=1.10.1
SCALA_VERSION=3.5.0
JAVA_VERSION=17.0.10_7
docker pull sbtscala/scala-sbt:eclipse-temurin-jammy-${JAVA_VERSION}_${SBT_VERSION}_${SCALA_VERSION}
```

Exécuter

```bash
docker run -it --rm sbtscala/scala-sbt:eclipse-temurin-jammy-${JAVA_VERSION}_${SBT_VERSION}_${SCALA_VERSION}
```

```bash
docker run -it --rm -v ./spark_example/:/spark_exam
ple/ sbtscala/scala-sbt:eclipse-temurin-jammy-${JAVA_VERSION}_${SBT_VERSION}_${SCALA_VERSION}
```

Dans le container :

```
cd /spark_example
sbt
compile
run
```
