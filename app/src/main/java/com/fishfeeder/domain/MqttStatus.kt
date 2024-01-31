package com.fishfeeder.domain

sealed class MqttStatus private constructor() {
    data object Connected : MqttStatus()

    data class Error(val error: Throwable) : MqttStatus()

    data object Disconnected : MqttStatus()
}