package highApi;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Create by Zhao Qing on 2018/4/26
 * highApi.SimpleKafkaProducer
 */
public class SimpleKafkaProducer {
    public static void main(String[] args){

        String kafka_server = "10.87.52.135:9092,10.87.52.134:9092,10.87.52.158:9092";
//        String kafka_zk = "10.87.52.135:2181,10.87.52.134:2181,10.87.52.158:2181/kafka-0.10.1.1";
        String topic = "basetest";
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", kafka_server);
        props.setProperty("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        while (true){
            String value = Long.toString(System.currentTimeMillis());
            producer.send(new ProducerRecord<String, String>(topic, value, value));
        }
    }
}
