package com.fishfeeder.ui.screens.home

import android.app.Application
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.fishfeeder.data.local.entity.ScheduleEntity
import com.fishfeeder.data.local.util.UiState
import com.fishfeeder.data.repository.FishFeederRepository
import com.fishfeeder.domain.MqttResult
import com.fishfeeder.services.MqttService
import com.fishfeeder.utils.Constants
import com.fishfeeder.utils.convertTimeToMillis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val fishFeederRepository: FishFeederRepository
) : AndroidViewModel(application) {
    private var timer: CountDownTimer? = null
    private val initialTime = MutableLiveData<Long>()
    private val currentTime = MutableLiveData<Long>()
    var currentTimeString by mutableStateOf("")

    private val _eventCountDownFinish = MutableLiveData<Boolean>()
    val eventCountDownFinish: LiveData<Boolean> = _eventCountDownFinish

    private val _onCounting: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val onCounting: StateFlow<Boolean>
        get() = _onCounting

    private val _scheduleState: MutableStateFlow<UiState<ScheduleEntity>> =
        MutableStateFlow(UiState.Loading)
    val scheduleState: StateFlow<UiState<ScheduleEntity>>
        get() = _scheduleState.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState.Loading
        )

    private val applicationContext = getApplication<Application>().applicationContext

    private val _turbidityStatus: MutableStateFlow<MqttResult> =
        MutableStateFlow(MqttResult.ConnectionLost(null))
    val turbidityStatus: StateFlow<MqttResult>
        get() = _turbidityStatus

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.CallServo -> callServo()
        }
    }

    private fun callServo() {
        Intent(applicationContext, MqttService::class.java).let {
            it.action = Constants.ACTION_SERVO
            applicationContext.startService(it)
        }
    }

    fun getNearestSchedule() {
        viewModelScope.launch {
            try {
                val schedule = fishFeederRepository.getNearestSchedule().catch { exception ->
                    _scheduleState.value = UiState.Error(exception.message.orEmpty())
                }.collect { schendule ->
                    Log.d("Schedule Viewmode", schendule.title ?: "Kosong")
                    startTimer(schendule.hour)
                    _scheduleState.value = UiState.Success(schendule)
                }

            } catch (exception: Exception) {
                _scheduleState.value = UiState.Error(exception.message.orEmpty())
            }
        }
    }

    private fun updateTimeString(millisUntilFinished: Long) {
        currentTimeString = DateUtils.formatElapsedTime(millisUntilFinished / 1000)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun startTimer(scheduledTime: String) {

        timer?.start()
        val currentTimeMillis = System.currentTimeMillis()
        val scheduleTimeMillis = convertTimeToMillis(scheduledTime)
        val initialTimeMillis = scheduleTimeMillis - currentTimeMillis
        initialTime.value = initialTimeMillis
        currentTime.value = initialTimeMillis
        timer = object : CountDownTimer(initialTimeMillis, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                currentTime.value = millisUntilFinished
                updateTimeString(millisUntilFinished)
                _onCounting.value = true
            }

            override fun onFinish() {
                resetTimer()


            }
        }


    }

    fun resetTimer() {
        timer?.cancel()
        currentTime.value = initialTime.value
        _eventCountDownFinish.value = true
        _onCounting.value = false


    }

    fun turbidityObserve() {
        viewModelScope.launch {
            MqttService.serviceResult.asFlow()
                .catch {
                    _turbidityStatus.value = MqttResult.ConnectionLost(it)
                }
                .collect {
                _turbidityStatus.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

}