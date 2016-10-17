import scala.concurrent.Future

import org.apache.kafka.clients.producer.RecordMetadata
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.KafkaProducer

import com.typesafe.config.ConfigFactory
import com.typesafe.config.Config

case class KafkaScalaProducer(producer: KafkaProducer[Integer, String], topicName: String) {
  def sendMsg(msg: String): Unit = {
    val record = new ProducerRecord[Integer, String](s"$topicName", 1, s"$msg")
    val metaDataFuture = producer.send(record)

    /*val metaInfo = metaDataFuture.get() // blocking!

      val msgLog = s"""offset    = ${metaInfo.offset()}  partition = ${metaInfo.partition()} topic     = ${metaInfo.topic()} """.stripMargin
      println(s"Interation = $i $msgLog ")*/

    //}

  }
  def closeProducer() = { producer.close() }

}

object KafkaScalaProducer {
  def apply(confToUse: String) = {
    val kafkaProducerConf = ConfigFactory.load(confToUse)
    checkThenGetProperties(kafkaProducerConf) match {
      case Some(properties) => {

        try {
          val kfp = new KafkaProducer[Integer, String](properties)
          new KafkaScalaProducer(kfp, s"${kafkaProducerConf.getString("topicName")}")
        } catch {

          case ex: org.apache.kafka.common.KafkaException =>
            {

              ex.getCause match {
                case configEx: org.apache.kafka.common.config.ConfigException => s"${configEx.getMessage}"
              }
            }
          case t: Throwable => t.printStackTrace(); t.getMessage // any unhandled error will go here
          //org.apache.kafka.common.config.ConfigException: No resolvable bootstrap urls given in bootstrap.servers
        }

      }
      case _ => None
    }

  }

  def checkThenGetProperties(producerPropertyFile: Config): Option[java.util.Properties] = {
    //val conf = ConfigFactory.load(producerPropertyFile)
    val props = new java.util.Properties()
    (producerPropertyFile.hasPath("bootstrap.servers"),
      producerPropertyFile.hasPath("client.id"),
      producerPropertyFile.hasPath("key.serializer"),
      producerPropertyFile.hasPath("value.serializer")) match {
        case (true, true, true, true) => {
          props.put("bootstrap.servers", producerPropertyFile.getString("bootstrap.servers"))
          props.put("client.id", producerPropertyFile.getString("client.id"))
          props.put("key.serializer", producerPropertyFile.getString("key.serializer"))
          props.put("value.serializer", producerPropertyFile.getString("value.serializer"))
          //you can add here rest of the properties, for reference have look at kafkaProducer.properties in resources
          Option(props)
        }
        case (_, _, _, _) => None
      }

  }

}