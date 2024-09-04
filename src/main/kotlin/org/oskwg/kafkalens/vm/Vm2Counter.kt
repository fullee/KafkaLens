package org.oskwg.kafkalens.vm

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.koinInject

@Preview
@Composable
fun Vm2Counter() {
    val vm = koinInject<MessageViewModel>()
    val messageList by vm.message.collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        println("startConsumerMessages")
        vm.startConsumerMessages()
    }

    LazyColumn {
        item {
            Column {
                Text(text = "MessageList")
                Button(onClick = vm::clearMessageList) {
                    Text(text = "clearMessageList")
                }
                Button(onClick = { vm.sendMessage("111")}) {
                    Text(text = "sendMessage")
                }
            }
        }
        items(messageList) {
            Text(text = it)
        }
    }
}