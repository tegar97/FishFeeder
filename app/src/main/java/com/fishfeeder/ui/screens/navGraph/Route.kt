package com.fishfeeder.ui.screens.navGraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object HomeScreen : Route(route = "homeScreen")

    object ListScheduleScreen : Route(route = "listScheduleScreen")

    object AddingScheduleScreen : Route(route = "addingScheduleScreen")

    object FishFeederNavigation : Route(route = "fishFeederNavigation")

    object ClassifyImageScreen : Route(route = "classifyImageScreen")
}
