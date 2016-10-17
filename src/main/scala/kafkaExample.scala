import scala.concurrent.Future

import org.apache.kafka.clients.producer.RecordMetadata
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.KafkaProducer

import com.typesafe.config.ConfigFactory
import com.typesafe.config.Config

object KafkaScalaProducer {
  def sendMsg(msg: String, conf: String): Unit = {
    val kafkaProducerConf = ConfigFactory.load(conf)
    // you need to provide java.util.Properties to KafkaProducer's constructor 
    val producer = new KafkaProducer[Integer, String](getProperties(kafkaProducerConf))

    //for (i <- 1 to 10) {
    //val record = new ProducerRecord[Integer, String](kafkaProducerConf.getString("topicName"), 1, s"ubi test @ [${java.util.Calendar.getInstance().getTime()}] iteration num=$i")
    val record = new ProducerRecord[Integer, String](kafkaProducerConf.getString("topicName"), 1, s"$msg")
    val metaDataFuture = producer.send(record)

    /*val metaInfo = metaDataFuture.get() // blocking!

      val msgLog = s"""offset    = ${metaInfo.offset()}  partition = ${metaInfo.partition()} topic     = ${metaInfo.topic()} """.stripMargin
      println(s"Interation = $i $msgLog ")*/

    //}
    producer.close()
  }

  def getProperties(producerPropertyFile: Config) = {
    //val conf = ConfigFactory.load(producerPropertyFile)
    val props = new java.util.Properties()
    props.put("bootstrap.servers", producerPropertyFile.getString("bootstrap.servers"))
    props.put("client.id", producerPropertyFile.getString("client.id"))
    props.put("key.serializer", producerPropertyFile.getString("key.serializer"))
    props.put("value.serializer", producerPropertyFile.getString("value.serializer"))
    //you can add here rest of the properties, for reference have look at kafkaProducer.properties in resources
    props
  }

}