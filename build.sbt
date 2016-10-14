name := "kafkaExample"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

//libraryDependencies += "com.typesafe.akka" % "akka-actor_2.10" % "2.2-M1"
libraryDependencies += "com.typesafe" % "config" % "1.3.1"

retrieveManaged := true // to copy library into lib_managed

// https://mvnrepository.com/artifact/org.apache.kafka/kafka_2.10
//libraryDependencies += "log4j" % "log4j" % "1.2.15" exclude("javax.jms", "jms")
//libraryDependencies += "org.apache.kafka" % "kafka_2.10" % "0.8.0"exclude("javax.jms", "jms")exclude("com.sun.jdmk", "jmxtools")exclude("com.sun.jmx", "jmxri")
// ref : http://nverma-tech-blog.blogspot.com/2015/12/apache-kafka-example-of.html


// https://mvnrepository.com/artifact/org.apache.kafka/kafka_2.10
libraryDependencies += "org.apache.kafka" % "kafka_2.10" % "0.10.0.0"

