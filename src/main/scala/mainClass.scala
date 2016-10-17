

object mainClass extends App {
  val ff = Option(KafkaScalaProducer("kafkaProducer"))

  ff match {
    case Some(ff: KafkaScalaProducer) => {
      for (i <- 0 to 1) {
        ff.sendMsg(s"Current time is ${java.util.Calendar.getInstance().getTime()}")
      }

      println("closing producer")
      ff.closeProducer()
      println("Done From Running")
    }
    case Some(exceptionMsg:String) => println(exceptionMsg)
    case _ =>println("Unable to get instance of KafkaScalaProducer")
  }

}

//kafka exceptions docs: https://kafka.apache.org/0100/javadoc/org/apache/kafka/common/KafkaException.html