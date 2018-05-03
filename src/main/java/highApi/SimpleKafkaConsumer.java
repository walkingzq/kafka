package highApi;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Create by Zhao Qing on 2018/4/26
 * highApi.SimpleKafkaConsumer
 */
public class SimpleKafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(SimpleKafkaConsumer.class);

    public static void main(String[] args){
        String kafka_server = "10.87.52.135:9092,10.87.52.134:9092,10.87.52.158:9092";
//        String kafka_zk = "10.87.52.135:2181,10.87.52.134:2181,10.87.52.158:2181/kafka-0.10.1.1";
        String topic = "test";
        Properties props = new Properties();
        props.put("bootstrap.servers", kafka_server);
        props.put("group.id", "kafka_consumer");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));
        Map<Long, Long> countMap = new HashMap<>();
//        TopicPartition partition = new TopicPartition(topic, 1);
//        consumer.seek(partition, 2);//消费指定分区指定位置
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records){
                Long key = Long.parseLong(record.value()) / 1000L;
                if (countMap.get(key) == null){
                    countMap.put(key, 1L);
                }else {
                    countMap.put(key, countMap.get(key) + 1);
                }
            }
            for (long key : countMap.keySet()){
                System.out.println("key:" + key + ",value:" + countMap.get(key));
            }

        }
    }
}
