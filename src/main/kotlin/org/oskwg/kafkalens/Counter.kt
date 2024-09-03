package org.oskwg.kafkalens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Counter(count: Int, onIncrement: () -> Unit, onDecrement: (String) -> Unit) {
    Column {
        Text(text = count.toString())
        Button(onClick = onIncrement) {
            Text(text = "Increment")
        }
        Button(onClick = { onDecrement("") }) {
            Text(text = "Decrement")
        }
    }
}