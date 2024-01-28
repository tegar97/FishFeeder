package com.fishfeeder.ui.screens.home

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.fishfeeder.services.MqttService
import com.fishfeeder.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(application : Application) : AndroidViewModel(application) {

    private val applicationContext = getApplication<Application>().applicationContext
    fun onEvent(event: HomeEvent) {
        when(event) {
            HomeEvent.CallServo -> callServo()
        }
    }

    private fun callServo() {
        Intent(applicationContext, MqttService::class.java).let {
            it.action = Constants.ACTION_SERVO
            applicationContext.startService(it)
        }
    }
}