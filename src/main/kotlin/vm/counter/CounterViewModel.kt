package vm.counter

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CounterViewModel : ViewModel(){
//    private val _counter = MutableStateFlow(0)
//    val counter = _counter.asStateFlow()
    private val _counter = mutableStateOf(0)
    val counter = _counter
    fun increment(){
        _counter.value++
    }

    fun decrement(){
        _counter.value--
    }
}