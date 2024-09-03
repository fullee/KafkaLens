package org.oskwg.kafkalens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.apache.kafka.clients.admin.KafkaAdminClient
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.config.SaslConfigs
import java.util.*

@Composable
@Preview
fun App() {


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
    props["key.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
    props["value.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"

//    KafkaProducer<String, String>(props).use { producer ->
//        producer.send(ProducerRecord("myTopic", "Hello World!"))
//    }

    val client = KafkaAdminClient.create(props)

    client.describeConsumerGroups(listOf("first_topic"))
    val listConsumerGroups = client.listConsumerGroups()
    listConsumerGroups.all().get().forEach { println("consumerGroup: ${it.groupId()} ${it}") }

    var text by remember { mutableStateOf("Hello, World!") }
    var inputText by rememberSaveable { mutableStateOf("input") }
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

    val controller by remember { mutableStateOf(client.describeCluster().controller().get().id()) }
    var messageList by remember { mutableStateOf(listOf<String>()) }

    val scope = rememberCoroutineScope()

//    LaunchedEffect(Unit) {
//
//        props["group.id"] = "my-consumer-group2"
//        props["auto.offset.reset"] = "earliest"
//        KafkaConsumer<String, String>(props).use { customer ->
//            customer.subscribe(listOf("first_topic"))
//            while (true) {
//                customer.poll(Duration.ofMillis(200)).forEach { record -> messageList = messageList + record.value() }
//                delay(500)
//            }
//        }
//    }



    MaterialTheme {
        Column {
            Row {
                Column {
                    Button(onClick = {
                        text = "Hello, Desktop!"
                    }) {
                        Text(text)
                    }
                    Text(text = inputText, modifier = Modifier.padding(start = 8.dp))
                    Text(text = "TT")
                    Text(text = "TT")
                    client.describeCluster().nodes().get().forEach {
                        Text(text = it.toString())
                    }

                    Text(text = controller.toString())
                    TextField(value = inputText, onValueChange = { inputText = it }, label = { Text("Label: ") })
                    Image(
                        painterResource("01fc9873cc315d17ec292f5cf887f525~90x90.webp"),
                        contentDescription = "icon",
                        modifier = Modifier.padding(start = 8.dp).clip(
                            CircleShape
                        )
                    )
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextField(value = inputText, onValueChange = { inputText = it }, label = { Text("message：") })
                Button(onClick = {
                    scope.launch(Dispatchers.IO) {
                        sendMessage(props, inputText)
                    }
                }, modifier = Modifier.padding(start = 8.dp)) {
                    Text(text = "Send")
                }
            }
            LazyColumn(modifier = Modifier.padding(start = 8.dp).fillMaxHeight().fillMaxWidth()) {
                items(messageList) {
                        message -> Text(text = "receiver: $message", modifier = Modifier.background(Color.Green).fillMaxWidth(), color = Color.Red)
                }
            }


        }
    }
}

fun sendMessage(props: Properties, message: String) {
    KafkaProducer<String, String>(props).use { producer ->
        producer.send(ProducerRecord("first_topic", message, message))
    }
}