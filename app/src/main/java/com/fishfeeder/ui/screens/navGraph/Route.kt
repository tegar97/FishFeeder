package com.fishfeeder.ui.screens.navGraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    data object HomeScreen : Route(route = "homeScreen")

    data object ListScheduleScreen : Route(route = "listScheduleScreen")

    data object AddingScheduleScreen : Route(route = "addingScheduleScreen")

    data object FishFeederNavigation : Route(route = "fishFeederNavigation")

    data object ClassifyImageScreen : Route(route = "classifyImageScreen")

    data object TurbidityScreen : Route(route = "turbidityScreen")
}
