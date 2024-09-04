package org.oskwg.kafkalens.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.kafka.clients.admin.AdminClient
import org.koin.core.annotation.Single

@Single
class CounterViewModel(private val kafkaClient: AdminClient) : ViewModel() {
    private val _counter = MutableStateFlow(0)

    //    private val kafkaClient = koinInject<AdminClient>()
    val counter = _counter.asStateFlow()


    fun topics() {
        _counter.value+=100
        println("main: ${Thread.currentThread().name}")
        viewModelScope.launch {
            println("sub: ${Thread.currentThread().name}")
//            kafkaClient.listTopics().names().get().forEach { println("topic: $it") }
            val aa = withContext(Dispatchers.IO) {
                println("withContext: ${Thread.currentThread().name}")
                kafkaClient.listTopics().names().get().forEach { println("topic: $it") }
            }

            println("return $aa")
        }
    }

    fun increment() {
        println("aaaa")
        viewModelScope.launch {
            delay(2000)
            _counter.value++
        }
    }

    fun decrement() {

        _counter.value--
    }
}