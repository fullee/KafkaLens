import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.*
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import vm.counter.VmCounter


object TrayIcon : Painter() {
    override val intrinsicSize = Size(256f, 256f)

    override fun DrawScope.onDraw() {
        drawOval(Color(0xFFFFA500))
    }
}

fun main() = application {
    var isOpen by remember { mutableStateOf(true) }

    val trayState = rememberTrayState()
    val notification = rememberNotification("Notification", "Message from MyApp!")

    Tray(
        state = trayState,
        icon = TrayIcon,
        menu = {
            Item(
                "Increment value",
                onClick = {
                }
            )
            Item(
                "Send notification",
                onClick = {
                    trayState.sendNotification(notification)
                }
            )
            Item(
                "Exit",
                onClick = {
                    isOpen = false
                }
            )
        }
    )

    val channel = remember { Channel<CounterAction>() }
    val flow = remember(channel) { channel.consumeAsFlow() }
    rememberScaffoldState()
    val state = CounterPresenter(flow)
    Window(
        onCloseRequest = ::exitApplication,
        title = "Kotlin Compose",
        icon = painterResource("kafka_logo--simple.png") /*undecorated = true*/
    ) {

        Surface(color = Color.Gray, modifier = Modifier.fillMaxSize().background(Color.Red)) {
            /*App()*/
//            Counter(
//                count = state.count, onIncrement = { channel.trySend(CounterAction.Increment) },
//                onDecrement = { channel.trySend(CounterAction.Decrement)}
//            )
            VmCounter(vm = viewModel())
        }
    }
}
