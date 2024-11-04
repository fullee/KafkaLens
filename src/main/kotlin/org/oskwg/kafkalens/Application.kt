package org.oskwg.kafkalens

import androidx.compose.runtime.Composable
import org.oskwg.kafkalens.window.ConfigurationWindow

@Composable
fun Application(onCloseRequest: () -> Unit) {


    ConfigurationWindow(onCloseRequest = { onCloseRequest() })
}