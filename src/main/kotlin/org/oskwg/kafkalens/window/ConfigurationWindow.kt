package org.oskwg.kafkalens.window

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import org.koin.compose.koinInject
import org.oskwg.kafkalens.vm.MainWindowViewModel

@Composable
fun ConfigurationWindow(
    onCloseRequest: () -> Unit,
) {

    val mainWindowViewModel = koinInject<MainWindowViewModel>()
    var selectedItem by remember { mutableStateOf("Item 1") }


    val mainWindowShow by mainWindowViewModel.mainWindowShow.collectAsState()
    val configWindowShow by mainWindowViewModel.configWindowShow.collectAsState()

    if (mainWindowShow) {
        MainWindow()
    }

    Window(
        visible = configWindowShow,
        onCloseRequest = { onCloseRequest() },
        title = "KafkaLens",
        icon = painterResource("kafka_logo--simple.png"), /*undecorated = true*/
        resizable = false
    ) {
        MenuBar {
            Menu("File") {
                Item("New window", onClick = {
                })
                Item("Exit", onClick = {
                    onCloseRequest()
                })
            }
        }
        Row(modifier = Modifier.fillMaxSize()) {
            KafkaClusterItemList(selectedItem = selectedItem, onItemSelected = { selectedItem = it })
            KafkaClusterEdit()
        }

    }
}


@Preview
@Composable
fun PreviewView() {
    Row {
        Text("Hello")
        IconButton(onClick = {}, modifier = Modifier.clip(
            CircleShape).background(color = androidx.compose.ui.graphics.Color.Red)) {
            Icon(
                Icons.Filled.Add, contentDescription = null,
            )
        }
    }
}