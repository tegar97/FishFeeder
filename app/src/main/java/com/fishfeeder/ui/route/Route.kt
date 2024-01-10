package com.fishfeeder.ui.route

sealed class Route(val route: String) {

    data object Home : Route("home")


}