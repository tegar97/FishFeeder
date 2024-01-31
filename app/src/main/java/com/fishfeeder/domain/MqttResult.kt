package com.fishfeeder.domain

import org.eclipse.paho.client.mqttv3.MqttMessage

sealed class MqttResult private constructor() {
    data class MessageArrived(val topic : String?, val massage : MqttMessage?) : MqttResult()

    data class ConnectionLost(val error: Throwable?) : MqttResult()

    data object DeliveryComplete : MqttResult()
}