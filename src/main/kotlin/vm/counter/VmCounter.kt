package vm.counter

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun VmCounter(vm: CounterViewModel = viewModel()){
    Column {
        Text(text = vm.counter.value.toString())
        Button(onClick = vm::increment) {
            Text(text = "Increment")
        }
        Button(onClick = vm::decrement) {
            Text(text = "Decrement")
        }
    }

}