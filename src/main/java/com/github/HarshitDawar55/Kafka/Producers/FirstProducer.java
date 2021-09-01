package com.github.HarshitDawar55.Kafka.Producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class FirstProducer {
    public static void main(String[] args) {
        // Creating Required Variables
        String BootStrap_Servers = "localhost:9092";

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
        producer.send(record);

        // Flushing the Data & then closing the Producer!
        producer.flush();
        producer.close();
    }
}
