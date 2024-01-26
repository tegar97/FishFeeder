package com.fishfeeder.ui.screens.adding

sealed class AddingEvent {
    data class SaveSchedule(val timeResult : String, val nameSchedule: String) : AddingEvent()

}