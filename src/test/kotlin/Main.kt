package org.example

import org.apache.kafka.clients.admin.KafkaAdminClient
import org.apache.kafka.common.config.SaslConfigs
import java.util.*


fun main() {

    val username = "ZmFpdGhmdWwtaG9uZXliZWUtMTA5NTgkRRCktSbSc85odqixDJroDDQIz95f-co"
    val password = "MjlhMWMxZjAtZTcyOC00MDI2LWE0NzUtNWQ4NDVkOTgxZmE3"

    val props = Properties()
    props["bootstrap.servers"] = "https://faithful-honeybee-10958-us1-kafka.upstash.io:9092"
    props["sasl.mechanism"] = "SCRAM-SHA-256"
    props["security.protocol"] = "SASL_SSL"
    props[SaslConfigs.SASL_JAAS_CONFIG] =
        """
            org.apache.kafka.common.security.scram.ScramLoginModule required
            username="$username"
            password="$password";
            """.trimMargin()
    props["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
    props["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"

//    KafkaProducer<String, String>(props).use { producer ->
//        producer.send(ProducerRecord("myTopic", "Hello World!"))
//    }

    val client = KafkaAdminClient.create(props)
    // 打印主题名称
    client.listTopics().names().get().forEach { println("主题名称： $it") }
    // 打印主题的分区信息
    val topicDescription = client.describeTopics(listOf("first_topic")).topicNameValues();
    topicDescription.forEach {
        println(
            "分区信息: ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"
        )
        it.value.get().partitions().forEach {
            // 分区
            println("part: ${it.partition()} --- leader: ${it.leader()} isr：${it.isr()}")
            // 该分区的副本信息
            it.replicas().forEach {
                println("replicas: ${it}")
            }
        }
    }

//    client.metrics().forEach { t, metric ->
//        run {
//            println("metric: ${t} --- ${metric}")
//        }
//    }
    val listConsumerGroups = client.listConsumerGroups()
    listConsumerGroups.all().get().forEach { println("consumerGroup: ${it.groupId()}") }


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