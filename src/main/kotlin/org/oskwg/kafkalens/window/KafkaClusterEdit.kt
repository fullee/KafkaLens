package org.oskwg.kafkalens.window

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import org.oskwg.kafkalens.vm.MainWindowViewModel

@Composable
fun KafkaClusterEdit() {

    val mainWindowViewModel = koinInject<MainWindowViewModel>()
    var clusterName by remember { mutableStateOf("") }
    var properties by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(4.dp)) {
        Text("Cluster Name")
        OutlinedTextField(value = clusterName, onValueChange = { inputClusterName ->
            println("Input: $inputClusterName")
            clusterName = inputClusterName
        })
        Text("Bootstrap servers")
        OutlinedTextField(value = "", onValueChange = {})
        Text("Advanced properties")
        OutlinedTextField(value = properties, onValueChange = {
            properties = it
        }, modifier = Modifier.fillMaxWidth(),
            minLines = 4,
            placeholder = {
                Text(
                    """
                like this:
                security.protocol=SASL_SSL
                sasl.mechanism=PLAIN
                sasl.jaas.config=org.apache.kafka.common.security.plain.PlainLoginModule required username="admin" password="admin";
            """.trimIndent()
                )
            }
        )
        Spacer(Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = {
                    mainWindowViewModel.openMainWindow()
                },
                modifier = Modifier.height(36.dp)
            ) {
                Text("Open")
            }
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = {
                    mainWindowViewModel.openMainWindow()
                }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xF9f9f9FF)),
                modifier = Modifier.height(36.dp)
            ) {
                Icon(Icons.Default.Refresh, contentDescription = "Add", modifier = Modifier.size(24.dp))
                Spacer(Modifier.width(ButtonDefaults.IconSpacing))
                Text("Test Connection")
            }
            Spacer(Modifier.width(8.dp))
            Icon(
                Icons.Default.CheckCircle,
                contentDescription = "Add",
                modifier = Modifier.size(24.dp),
                tint = Color(0xFF29a383)
            )
            Spacer(Modifier.width(4.dp))
            Text("Connected")
        }

    }
}
