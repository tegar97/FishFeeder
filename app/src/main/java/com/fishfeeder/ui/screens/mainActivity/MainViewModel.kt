package com.fishfeeder.ui.screens.mainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.fishfeeder.data.local.util.UiState
import com.fishfeeder.domain.MqttStatus
import com.fishfeeder.services.MqttService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _serviceConnected: MutableStateFlow<MqttStatus> =
        MutableStateFlow(MqttStatus.Disconnected)
    val serviceConnected: StateFlow<MqttStatus>
        get() = _serviceConnected

    init {
        viewModelScope.launch {
            MqttService.serviceStatus.asFlow()
                .catch {
                    _serviceConnected.value = MqttStatus.Error(it.cause!!)
                }
                .collect {
                _serviceConnected.value = it
            }
        }
    }
}