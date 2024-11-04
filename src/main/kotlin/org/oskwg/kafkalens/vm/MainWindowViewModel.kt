package org.oskwg.kafkalens.vm

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.common.protocol.types.Field.Bool
import org.koin.core.annotation.Single

@Single
class MainWindowViewModel() : ViewModel() {
    private val _mainWindowShow = MutableStateFlow(false)
    private val _configWindowShow = MutableStateFlow(true)
    val mainWindowShow = _mainWindowShow.asStateFlow()
    val configWindowShow = _configWindowShow.asStateFlow()

    fun openMainWindow() {
        _mainWindowShow.value = true
        _configWindowShow.value = false
    }

    fun hideMainWindow() {
        _mainWindowShow.value = false
    }
    fun hideConfigWindow() {
        _configWindowShow.value = false
    }

}