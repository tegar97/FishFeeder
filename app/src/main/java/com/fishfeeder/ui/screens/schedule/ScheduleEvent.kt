package com.fishfeeder.ui.screens.schedule


sealed class ScheduleEvent {
    data class OnChangeSchedule(val id : Int, val isActive : Boolean) : ScheduleEvent()
}