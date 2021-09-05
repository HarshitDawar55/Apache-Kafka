package com.github.HarshitDawar55.Kafka.Producers;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class FirstConsumer {
    public static void main(String[] args) {
        // Creating Logger
        Logger logger = LoggerFactory.getLogger(FirstConsumer.class.getName());

        // Declaring the bootstrap servers
        String Bootstrap_Server = "localhost:9092";
        String groupID = "my-first-application";
        String topic_name = "first_topic";

        // Creating the Properties for the consumer
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Bootstrap_Server);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupID);

        // Creating a Consumer!
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        // Subscribe Consumer to the Topics!
        consumer.subscribe(Arrays.asList(topic_name));

        // Asking for the Data
        while(true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));

            for(ConsumerRecord<String, String> record : records){
                logger.info("Key: " + record.key() + ", Value: " + record.value());
                logger.info("Partition: " + record.partition() + ", Offset: " + record.offset());
            }
        }
    }
}
