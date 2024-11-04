package org.oskwg.kafkalens.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import java.time.Duration
import java.time.LocalDateTime
import java.util.*


//@Single
class MessageViewModel(@Named("kafkaProperties") private val props: Properties) : ViewModel() {
    private val _message = MutableStateFlow<List<String>>(emptyList())
    val message = _message.asStateFlow()

    fun startConsumerMessages() {

        println("main: ${Thread.currentThread().name}")
        viewModelScope.launch {
            println("sub: ${Thread.currentThread().name}")
            withContext(Dispatchers.IO) {
                println("withContext: ${Thread.currentThread().name}")
                props["group.id"] = "my-consumer-group2"
                props["auto.offset.reset"] = "earliest"
                KafkaConsumer<String, String>(props).use { customer ->
                    customer.subscribe(listOf("first_topic"))
                    customer.assignment().forEach {
                        customer.seek(it, 0)
                    }
                    while (true) customer.poll(Duration.ofMillis(200))
                        .forEach { record ->
                            println("withContext: ${Thread.currentThread().name} ${record.value()}")
                            _message.value = message.value + listOf("now: ${LocalDateTime.now()} - " + record.value())
                        }
                }
            }
        }
    }

    fun clearMessageList() {
        _message.value = emptyList()
    }

    fun sendMessage(message: String) {
        println("sendMessage: $message")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                KafkaProducer<String, String>(props).use { producer ->
                    producer.send(ProducerRecord("first_topic", message, LocalDateTime.now().toString()))
                }
            }
        }
    }
}