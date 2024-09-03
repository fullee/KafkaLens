package org.example

import org.apache.kafka.clients.consumer.KafkaConsumer
import java.time.Duration
import java.util.*


fun main() {
    val props = Properties()
    props["bootstrap.servers"] = "http://192.168.56.103:19092"
//    props["sasl.mechanism"] = "SCRAM-SHA-256"
//    props["security.protocol"] = "SASL_SSL"
//    props["sasl.jaas.config"] =
//        "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"c2VjdXJlLWhvb2t3b3JtLTk5NjAkLRwqapeL7YFt2g0rDnRZeZJV1JIPMUf-kYQ\" password=\"MGFjYWJmZGUtZmE0Yy00MDA2LThlZGQtMzE3ZDExNDRlYmQ4\";"
    props["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
    props["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
    props["key.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
    props["value.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
    props["group.id"] = "my-consumer-group"

    KafkaConsumer<String, String>(props).use { customer ->
        customer.subscribe(listOf("myTopic"))
        while (true) {
            customer.poll(Duration.ofSeconds(1)).forEach { record -> println(record.value()) }
        }
    }

//    KafkaProducer<String, String>(props).use { producer ->
//        producer.send(ProducerRecord("myTopic", "Hello World!"))
//    }
//
//    val client = KafkaAdminClient.create(props)
//    // 打印主题名称
//    client.listTopics().names().get().forEach { println("主题名称： $it") }
//    // 打印主题的分区信息
//    val topicDescription = client.describeTopics(listOf("myTopic")).topicNameValues();
//    topicDescription.forEach {
//        println("分区信息: ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
//        it.value.get().partitions().forEach {
//            // 分区
//            println("part: ${it.partition()} --- leader: ${it.leader()} isr：${it.isr()}")
//            // 该分区的副本信息
//            it.replicas().forEach{
//                println("replicas: ${it}")
//            }
//        }
//    }
//
////    client.metrics().forEach { t, metric ->
////        run {
////            println("metric: ${t} --- ${metric}")
////        }
////    }
//    val listConsumerGroups = client.listConsumerGroups()
//    listConsumerGroups.all().get().forEach { println("consumerGroup: ${it.groupId()}") }


//    for (consumerGroupListing in client.listConsumerGroups().all().get()) {
//        consumerGroupListing.state().ifPresent { println(it) }
//    }
//    client.metrics().forEach({ println(it) })
//    client.describeCluster().clusterId().get().let { println(it) }
//    client.describeCluster().nodes().get().forEach { println("nodes: $it") }
//    client.describeCluster().controller().get().let { println("controller: $it") }

    // nodes
    // topics
    // topic.partitions
    // topic.partitions.replicas
    // consumerGroups
    // consumerGroups.members
    // consumerGroups.members.assignment
    // consumerGroups.members.assignment.partitions
    // consumerGroups.members.assignment.partitions.topic
    // consumerGroups.members.assignment.partitions.topic.partition
    // consumerGroups.members.assignment.partitions.topic.partition.offset
    // consumerGroups.members.assignment.partitions.topic.partition.offset.timestamp
    // consumerGroups.members.assignment.partitions.topic.partition.offset.timestamp.type
    // producers
    // producers.transactions

    // broker.rack
    // broker.version
    // broker.log.dirs
}