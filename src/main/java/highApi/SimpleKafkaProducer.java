package highApi;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.Random;

/**
 * Create by Zhao Qing on 2018/4/26
 * highApi.SimpleKafkaProducer
 */
public class SimpleKafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleKafkaProducer.class);

    public static void main(String[] args){

        if (args.length != 1){
            LOGGER.info("usage:java -jar <jar_name> <topicToProduce>");
            System.exit(1);
        }
        String topic = args[0];
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "10.87.52.135:9092,10.87.52.134:9092,10.87.52.158:9092");
        props.setProperty("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        while (true){
            String eventTime = Long.toString(System.currentTimeMillis());
            producer.send(new ProducerRecord<String, String>(topic, eventTime, eventTime + "," + randomWords()));
        }
//        int count = 0;
//        long start_time = System.currentTimeMillis();
//        long end_time = System.currentTimeMillis();
//        while (end_time - start_time <= 1000){
//            System.out.println(System.currentTimeMillis() + "," + randomWords());
//            end_time = System.currentTimeMillis();
//            count++;
//        }
//        System.out.println("startTime:" + start_time + ",endTime:" + end_time + ",duration:" + (end_time - start_time));
//        System.out.println("记录数：" + count);
    }


    public static String randomWords(){
        String[] words = new String[]{"hello", "world", "weibo", "start", "end", "haha", "compute", "yes", "no", "flink", "storm", "hadoop", "hdfs" ,
        "yarn", "logs", "log", "flume", "apache", "spark", "streaming", "spider"};
        Random random = new Random();
        int len = 0;
        while ((len = random.nextInt(7)) == 0);
        StringBuffer sb = new StringBuffer();
        String delim = ",";
        for (int i = 0; i < len; i++){
            sb.append(words[random.nextInt(words.length)]);
            if(i < len - 1){sb.append(delim);}
        }
        return sb.toString();
    }

}
