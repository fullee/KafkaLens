package org.oskwg.kafkalens

import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberNotification
import androidx.compose.ui.window.rememberTrayState
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.example.Database
import com.example.PlayerQueries
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.koin.ksp.generated.module
import org.oskwg.kafkalens.di.KoinModule
import org.oskwg.kafkalens.vm.MainWindowViewModel
import java.io.File


object TrayIcon : Painter() {
    override val intrinsicSize = Size(256f, 256f)

    override fun DrawScope.onDraw() {
        drawOval(Color(0xFFFFA500))
    }
}

fun main() = application {

    val scope = rememberCoroutineScope()

    val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:test.db")
    if (!File("test.db").exists()) {
        Database.Schema.create(driver)
    }

    val database = Database(driver)
    val playerQueries: PlayerQueries = database.playerQueries
    scope.launch(Dispatchers.IO) {
        val a = playerQueries.selectAll().asFlow().mapToList(Dispatchers.IO);
        a.collect { value -> println(value) }
    }

//    playerQueries.insert(player_number = 10, full_name = "Corey Perry")
//    println(playerQueries.selectAll().executeAsList())
//    // [HockeyPlayer(15, "Ryan Getzlaf"), HockeyPlayer(10, "Corey Perry")]
//
//    val player = HockeyPlayer(10, "Ronald McDonald")
//    playerQueries.insertFullPlayerObject(player)

    val trayState = rememberTrayState()
    val notification = rememberNotification("Notification", "Message from MyApp!")





//    val channel = remember { Channel<CounterAction>() }
//    val flow = remember(channel) { channel.consumeAsFlow() }
//    rememberScaffoldState()
//    val state = CounterPresenter(flow)
//    Window(
//        // 标题栏太丑，可以隐藏然后自定义
////        undecorated = true,
//        onCloseRequest = ::exitApplication,
//        title = "KafkaLens",
//        icon = painterResource("kafka_logo--simple.png") /*undecorated = true*/
//    ) {
////        KoinApplication(application = {
////            printLogger()
////            modules(KoinModule().module)
////        }) {
//
////            val client: AdminClient = koinInject()
////
////            // 打印主题名称
////            client.listTopics().names().get().forEach { println("主题名称： $it") }
//
//        Surface(color = Color.Gray, modifier = Modifier.fillMaxSize().background(Color.Red)) {
//            /*App()*/
////            Counter(
////                count = state.count, onIncrement = { channel.trySend(CounterAction.Increment) },
////                onDecrement = { channel.trySend(CounterAction.Decrement)}
////            )
////                VmCounter(vm = viewModel())
////                Vm2Counter()
//        }
//
////        }
//    }
//    FlatLightFlatIJTheme.setup();
    FlatDarkFlatIJTheme.setup()


    KoinApplication(application = {
        printLogger()
        modules(KoinModule().module)
    }) {

        val mainWindowViewModel = koinInject<MainWindowViewModel>()

        Tray(
            state = trayState,
            icon = TrayIcon,
            onAction = {
                println("Tray icon was double clicked")
                mainWindowViewModel.openMainWindow()
            },
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
                        exitApplication()
                    }
                )
            }
        )

        Application(onCloseRequest = ::exitApplication)
    }
}
