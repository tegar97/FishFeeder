package com.fishfeeder.ui.screens.home

sealed class HomeEvent {
    data object CallServo : HomeEvent()
}