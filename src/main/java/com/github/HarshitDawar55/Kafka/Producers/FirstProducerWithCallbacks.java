package com.github.HarshitDawar55.Kafka.Producers;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class FirstProducerWithCallbacks {
    public static void main(String[] args) {
        // Creating Required Variables
        String BootStrap_Servers = "localhost:9092";
        Logger logger = LoggerFactory.getLogger(FirstProducerWithCallbacks.class);

        // Setting Producer Required Properties using the best way instead of old way by using the exact names in strings
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BootStrap_Servers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Creating Kafka Producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        // Creating a Producer Record to send!
        ProducerRecord<String, String> record =
                new ProducerRecord<String, String>("First-Topic", "First Message from the Producer!");

        // Sending the Data
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                // This code will executes when a record is sent successfully or an exception is thrown
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
        });

        // Flushing the Data & then closing the Producer!
        producer.flush();
        producer.close();
    }
}
