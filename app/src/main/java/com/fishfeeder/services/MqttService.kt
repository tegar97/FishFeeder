package com.fishfeeder.services

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import com.fishfeeder.domain.MqttResult
import com.fishfeeder.domain.MqttStatus
import com.fishfeeder.utils.Constants.ACTION_SERVO
import com.fishfeeder.utils.Constants.ACTION_START_SERVICE
import com.fishfeeder.utils.Constants.ACTION_STOP_SERVICE
import com.fishfeeder.utils.Constants.BROKER_URL
import com.fishfeeder.utils.Constants.TOPIC_SERVO_PUBLISH
import com.fishfeeder.utils.Constants.TOPIC_TURBIDITY_NOMINAL
import com.fishfeeder.utils.Constants.TOPIC_TURBIDITY_STATUS
import dagger.hilt.android.AndroidEntryPoint
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.IMqttToken
import org.eclipse.paho.client.mqttv3.MqttAsyncClient
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage

@AndroidEntryPoint
class MqttService : LifecycleService(), IMqttActionListener, MqttCallback {

    private var username: String = ""
    private var password: String = ""
    private lateinit var mqttClient: MqttAndroidClient

    companion object {
        val serviceStatus = MutableLiveData<MqttStatus>()
        val serviceResult = MutableLiveData<MqttResult>()
    }

    override fun onCreate() {
        serviceResult.postValue(MqttResult.ConnectionLost(null))
        serviceStatus.postValue(MqttStatus.Disconnected)
        mqttClient = MqttAndroidClient(this, BROKER_URL, MqttAsyncClient.generateClientId())
        mqttClient.setCallback(this)
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                ACTION_START_SERVICE -> {
                    connect()
                    var isSubs = false
                    serviceStatus.observe(this) { value ->
                        if (value == MqttStatus.Connected && !isSubs) {
                            isSubs = true
                            subscribe(
                                topic = TOPIC_TURBIDITY_STATUS,
                                qos = 0
                            )
                            subscribe(
                                topic = TOPIC_TURBIDITY_NOMINAL,
                                qos = 0
                            )
                        }
                    }
                }

                ACTION_SERVO -> {
                    publish(
                        topic = TOPIC_SERVO_PUBLISH,
                        qos = 0,
                        msg = "1",
                    )
                }

                ACTION_STOP_SERVICE -> {
                    disconnect()
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun connect() {
        val options = MqttConnectOptions()
        options.userName = username
        options.password = password.toCharArray()
        options.isAutomaticReconnect = true
        try {
            mqttClient.connect(options, this)
            Log.d("Service", "connect")
        } catch (e: MqttException) {
            serviceStatus.postValue(MqttStatus.Error(e))
            e.printStackTrace()
        }
    }

    private fun subscribe(
        topic: String,
        qos: Int = 1
    ) {
        try {
            mqttClient.subscribe(topic, qos, null, this)
            Log.d("TAG", "subscribe")
        } catch (e: MqttException) {
            serviceStatus.postValue(MqttStatus.Error(e))
            e.printStackTrace()
        }
    }

    private fun unsubscribe(
        topic: String
    ) {
        try {
            mqttClient.unsubscribe(topic)
        } catch (e: MqttException) {
            serviceStatus.postValue(MqttStatus.Error(e))
            e.printStackTrace()
        }
    }

    private fun publish(
        topic: String,
        msg: String,
        qos: Int = 1,
        retained: Boolean = false
    ) {
        try {
            val message = MqttMessage()
            message.payload = msg.toByteArray()
            message.qos = qos
            message.isRetained = retained
            mqttClient.publish(topic, message, null, this)
        } catch (e: MqttException) {
            serviceStatus.postValue(MqttStatus.Error(e))
            e.printStackTrace()
        }
    }

    private fun disconnect() {
        try {
            mqttClient.disconnect()
            serviceStatus.postValue(MqttStatus.Disconnected)
            serviceResult.postValue(MqttResult.ConnectionLost(null))
        } catch (e: MqttException) {
            serviceStatus.postValue(MqttStatus.Error(e))
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        disconnect()
        super.onDestroy()
    }

    override fun onSuccess(asyncActionToken: IMqttToken?) {
        serviceStatus.postValue(MqttStatus.Connected)
    }

    override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
        exception?.message?.let { Log.e("Service", it) }
        serviceStatus.postValue(exception?.let { MqttStatus.Error(it) })
    }

    override fun connectionLost(cause: Throwable?) {
        serviceResult.postValue(MqttResult.ConnectionLost(cause))
    }

    override fun messageArrived(topic: String?, message: MqttMessage?) {
        serviceResult.postValue(MqttResult.MessageArrived(topic, message))
    }

    override fun deliveryComplete(token: IMqttDeliveryToken?) {
        serviceResult.postValue(MqttResult.DeliveryComplete)
    }
}