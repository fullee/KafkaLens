package org.oskwg.kafkalens.vm

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.koinInject

@Composable
fun Vm2Counter(){
    val vm = koinInject<CounterViewModel>()
    val counter by vm.counter.collectAsState(initial = 0)

    Column {
        Text(text = "Vm2Counter")
        Text(text = counter.toString())
        Button(onClick = vm::increment) {
            Text(text = "Increment")
        }
        Button(onClick = vm::decrement) {
            Text(text = "Decrement")
        }
    }

}