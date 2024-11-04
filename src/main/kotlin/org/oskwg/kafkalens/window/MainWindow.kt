package org.oskwg.kafkalens.window

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import org.koin.compose.koinInject
import org.oskwg.kafkalens.vm.MainWindowViewModel

@Composable
fun MainWindow() {

    val mainWindowViewModel = koinInject<MainWindowViewModel>()

    Window(
        onCloseRequest = { mainWindowViewModel.hideMainWindow() }, title = "H",
        icon = painterResource("kafka_logo--simple.png"), /*undecorated = true*/
        state = WindowState(width = 800.dp, height = 600.dp)
    ) {

        Text("Hello")
    }
}