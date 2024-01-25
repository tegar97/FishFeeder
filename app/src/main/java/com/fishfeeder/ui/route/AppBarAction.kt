package com.fishfeeder.ui.route

import com.fishfeeder.R
import com.fishfeeder.ui.screens.navigator.component.BottomNavigationItem

object AppBarAction {

    val HomeAppBar = listOf(
        BottomNavigationItem(R.drawable.baseline_camera_alt_24),
        BottomNavigationItem(R.drawable.baseline_alarm_on_24),
        BottomNavigationItem(R.drawable.baseline_restart_alt_24),

    )

    val ScheduleAppBar = listOf(
        BottomNavigationItem(R.drawable.baseline_add_24),
    )
}