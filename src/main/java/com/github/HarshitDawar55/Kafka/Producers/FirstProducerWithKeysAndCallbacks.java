package com.github.HarshitDawar55.Kafka.Producers;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class FirstProducerWithKeysAndCallbacks {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Creating Required Variables
        String BootStrap_Servers = "localhost:9092";
        Logger logger = LoggerFactory.getLogger(FirstProducerWithKeysAndCallbacks.class);

        // Setting Producer Required Properties using the best way instead of old way by using the exact names in strings
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BootStrap_Servers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Creating Kafka Producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);


        for(int i = 0; i < 10; i++){

            String key = "id_" + i;
            // Creating a Producer Record to send!
            ProducerRecord<String, String> record =
                    new ProducerRecord<String, String>("First-Topic", key, "First Message from the Producer!" + i);

           logger.info("Key: " + key);
            // Sending the Data
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    // This code will execute when a record is sent successfully or an exception is thrown
                    if (e == null){
                        logger.info("New MetaData received " + "\n" +
                                "Topic: " + recordMetadata.topic() + "\n" +
                                "Partition" + recordMetadata.partition() + "\n" +
                                "Offset" + recordMetadata.offset() + "\n" +
                                "Timestamp" + recordMetadata.timestamp()
                        );
                    } else {
                        logger.error(String.valueOf(e));
                    }
                }
            }).get();
        }


        // Flushing the Data & then closing the Producer!
        producer.flush();
        producer.close();
    }
}
