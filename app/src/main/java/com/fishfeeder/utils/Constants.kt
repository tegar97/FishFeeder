package com.fishfeeder.utils

object Constants {

    const val ACTION_START_SERVICE = "ACTION_START_SERVICE"
    const val ACTION_SERVO = "ACTION_SERVO"
    const val ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE"

    const val BROKER_URL = "tcp://free.mqtt.iyoti.id:1883"

    const val TOPIC_TURBIDITY_STATUS = "water/status"
    const val TOPIC_TURBIDITY_NOMINAL = "water/status1"
    const val TOPIC_SERVO_PUBLISH = "servo/publish"
}