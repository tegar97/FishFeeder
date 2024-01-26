package com.fishfeeder.ui.screens.navGraph

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.fishfeeder.R
import com.fishfeeder.ui.screens.adding.AddingViewModel
import com.fishfeeder.ui.screens.home.HomeViewModel
import com.fishfeeder.ui.screens.navigator.component.BottomNavigationItem
import com.fishfeeder.ui.screens.navigator.component.FishFeederTopBar
import com.fishfeeder.ui.screens.schedule.ScheduleViewModel

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    startDestination: String,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            when (currentRoute) {
                Route.HomeScreen.route -> {
                    val items = listOf(
                        BottomNavigationItem(R.drawable.baseline_alarm_on_24),
                        BottomNavigationItem(R.drawable.baseline_restart_alt_24)
                    )
                    FishFeederTopBar(
                        title = "FishFeeder",
                        items = items,
                        onItemClick = {

                        }
                    )
                }
                Route.ListScheduleScreen.route -> {
                    val items = listOf(
                        BottomNavigationItem(R.drawable.baseline_add_24)
                    )
                    FishFeederTopBar(
                        title = "Jadwal Makan",
                        items = items,
                        isCenteredTitle = true,
                        hasBackButton = true,
                        onItemClick = {

                        },
                        onBackClick = {

                        }
                    )
                }

                Route.AddingScheduleScreen.route -> {
                    FishFeederTopBar(
                        title = "Jadwal Makan",
                        hasBackButton = true,
                        isCenteredTitle = true,
                        onBackClick = {

                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier.padding(innerPadding)
        ) {
            navigation(
                startDestination = Route.HomeScreen.route,
                route = Route.FishFeederNavigation.route
            ) {
                composable(route = Route.HomeScreen.route) {
                    val viewModel: HomeViewModel = hiltViewModel()

                }
                composable(route = Route.AddingScheduleScreen.route) {
                    val viewModel: AddingViewModel = hiltViewModel()

                }
                composable(route = Route.ListScheduleScreen.route) {
                    val viewModel: ScheduleViewModel = hiltViewModel()

                }
            }
        }
    }
}