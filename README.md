# Apache-Kafka
This repository is for the Apache Kafka &amp; projects built based on the Apache Kafka!

## Starting Apache Kafka (Single Node Cluster)

1. Start Zoopkeeper Server:  zookeeper-server-start.sh config/zookeeper.properties
2. Start Kafka Server: kafka-server-start.sh config/server.properties

## Creating a New topic in the Kafka Cluster
kafka-topics.sh --zookeeper localhost:2181 --topic <topic name> --create --partitions <no. of partitions> --replication-factor '<replication factor>'
  
## Listing the Topics in the Kafka Cluster
kafka-topics.sh --zookeeper localhost:2181 --list
  
## Deleting the Topic in the Kafka Cluster
kafka-topics.sh --zookeeper localhost:2181 --topic <topic name> --delete
  

## Using a Producer in the Kafka Cluster
kafka-console-producer.sh --broker-list localhost:9092 --topic '<topic name to produce to>'
