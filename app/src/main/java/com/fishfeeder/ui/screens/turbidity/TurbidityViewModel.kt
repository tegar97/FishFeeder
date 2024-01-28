package com.fishfeeder.ui.screens.turbidity

import androidx.lifecycle.ViewModel
import com.fishfeeder.services.MqttService
import com.fishfeeder.ui.screens.schedule.ScheduleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.IMqttToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttMessage
import javax.inject.Inject

@HiltViewModel
class TurbidityViewModel @Inject constructor() : ViewModel() {
    fun onEvent(turbidityEvent: TurbidityEvent) {
        when (turbidityEvent) {

        }
    }

}