## Work is in Progress for this repository, it is not complete from my side!

# Apache-Kafka
This repository is for the Apache Kafka &amp; projects built based on the Apache Kafka!

## Starting Apache Kafka (Single Node Cluster)

1. Start Zoopkeeper Server:  zookeeper-server-start.sh config/zookeeper.properties
2. Start Kafka Server: kafka-server-start.sh config/server.properties

## Creating a New topic in the Kafka Cluster
kafka-topics.sh --zookeeper localhost:2181 --topic `<topic name>` --create --partitions `<no. of partitions>` --replication-factor `<replication factor>`
  
## Listing the Topics in the Kafka Cluster
kafka-topics.sh --zookeeper localhost:2181 --list
  
## Deleting the Topic in the Kafka Cluster
kafka-topics.sh --zookeeper localhost:2181 --topic `<topic name>` --delete
  

## Using a Producer in the Kafka Cluster
kafka-console-producer.sh --broker-list localhost:9092 --topic `<topic name to produce to>`

## Using a Consumer in the Kafka Cluster (To read from beginning)
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic `<topic name to read from>` --from-beginning

## Using a Consumer in the Kafka Cluster (To read only new messages)
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic `<topic name to read from>`

## Using a Consumer in a Consumer Group in the Kafka Cluster 
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic `<topic name to read from>` --group `<group name>`

## Getting the Consumer Groups
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list

## Describing a Consumer Groups
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group `<Consumer Group Name>`
