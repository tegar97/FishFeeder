package com.fishfeeder.ui.screens.schedule


sealed class ScheduleEvent {



    data class OnChangeSchedule(val id : Long, val isActive : Boolean) : ScheduleEvent()
}