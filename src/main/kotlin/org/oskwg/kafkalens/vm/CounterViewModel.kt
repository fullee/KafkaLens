package org.oskwg.kafkalens.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Single

@Single
class CounterViewModel : ViewModel(){
    private val _counter = MutableStateFlow(0)
    val counter = _counter.asStateFlow()
    fun increment(){
        println("aaaa")
        viewModelScope.launch {
            delay(2000)
            _counter.value++
        }
    }

    fun decrement(){

        _counter.value--
    }
}