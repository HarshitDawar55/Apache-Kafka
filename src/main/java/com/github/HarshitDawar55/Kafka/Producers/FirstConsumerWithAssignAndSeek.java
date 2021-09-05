package com.github.HarshitDawar55.Kafka.Producers;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class FirstConsumerWithAssignAndSeek {
    public static void main(String[] args) {
        // Creating Logger
        Logger logger = LoggerFactory.getLogger(FirstConsumerWithAssignAndSeek.class.getName());

        // Declaring the bootstrap servers
        String Bootstrap_Server = "localhost:9092";
        String topic_name = "first_topic";
        int NumberOfMessagesToRead = 7;
        boolean ContinueReading = true;
        int NumberOfMessagesAlreadyRead = 0;

        // Creating the Properties for the consumer
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Bootstrap_Server);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // Creating a Consumer!
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        // This Seek & Assign Capability is used when there is a need to replay a message or fetch a particular message
        // Assigning Consumer to a specific Topic Partition
        TopicPartition PartitionToReadFrom = new TopicPartition(topic_name, 0);
        int OffsetToReadFrom = 5;
        consumer.assign(Arrays.asList(PartitionToReadFrom));

        // Seeking the offset from a particular partition
        consumer.seek(PartitionToReadFrom, OffsetToReadFrom);

        // Asking for the Data
        while(ContinueReading){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));

            for(ConsumerRecord<String, String> record : records){
                NumberOfMessagesAlreadyRead += 1;
                logger.info("Key: " + record.key() + ", Value: " + record.value());
                logger.info("Partition: " + record.partition() + ", Offset: " + record.offset());
                if (NumberOfMessagesAlreadyRead >= NumberOfMessagesToRead){
                    ContinueReading = false;
                    break;
                }
            }
        }
        logger.info("Exiting Now From The Application!");
    }
}
