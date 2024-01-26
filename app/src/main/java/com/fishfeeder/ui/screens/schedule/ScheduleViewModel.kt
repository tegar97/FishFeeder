package com.fishfeeder.ui.screens.schedule

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor() : ViewModel() {
    fun onEvent(scheduleEvent: ScheduleEvent) {
        when (scheduleEvent) {
            is ScheduleEvent.OnChangeSchedule -> {

            }
        }
    }
}