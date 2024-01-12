package com.fishfeeder.ui.route

sealed class Route(val route: String) {

    data object Home : Route("home")
    data object Schedule : Route("schedule")
    data object Add : Route("add")


}