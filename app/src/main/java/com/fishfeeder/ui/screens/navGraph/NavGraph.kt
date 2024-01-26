package com.fishfeeder.ui.screens.navGraph

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.fishfeeder.domain.model.History
import com.fishfeeder.domain.model.Schedule
import com.fishfeeder.ui.screens.adding.AddingScreen
import com.fishfeeder.ui.screens.adding.AddingViewModel
import com.fishfeeder.ui.screens.classifyImage.ClassifyImageScreen
import com.fishfeeder.ui.screens.classifyImage.ClassifyImageViewModel
import com.fishfeeder.ui.screens.home.HomeScreen
import com.fishfeeder.ui.screens.home.HomeViewModel
import com.fishfeeder.ui.screens.navigator.component.BottomNavigationItem
import com.fishfeeder.ui.screens.navigator.component.FishFeederTopBar
import com.fishfeeder.ui.screens.schedule.ScheduleScreen
import com.fishfeeder.ui.screens.schedule.ScheduleViewModel

@RequiresApi(Build.VERSION_CODES.Q)
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
                            when (it) {
                                0 -> navController.navigate(Route.ListScheduleScreen.route)
//                                1 -> navController.navigate(Route.)
                            }
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
                            when (it) {
                                0 -> navController.navigate(Route.AddingScheduleScreen.route)
                            }
                        },
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }

                Route.AddingScheduleScreen.route -> {
                    FishFeederTopBar(
                        title = "Jadwal Makan",
                        hasBackButton = true,
                        isCenteredTitle = true,
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }

                Route.ClassifyImageScreen.route -> {
                    FishFeederTopBar(
                        title = "Cek Jenis Ikan",
                        hasBackButton = true,
                        isCenteredTitle = true,
                        onBackClick = {
                            navController.navigateUp()
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
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val histories = listOf(
                    History(id = 1, title = "Makan siang", hour = "2", status = true),
                    History(id = 2, title = "Makan malam", hour = "19", status = false),
                    History(id = 3, title = "Makan pagi", hour = "7", status = true),
                )
                HomeScreen(
                    histories = histories,
                    onEvent = viewModel::onEvent
                )
            }
            composable(route = Route.AddingScheduleScreen.route) {
                val viewModel: AddingViewModel = hiltViewModel()

                AddingScreen(onEvent = viewModel::onEvent)
            }
            composable(route = Route.ListScheduleScreen.route) {
                val viewModel: ScheduleViewModel = hiltViewModel()
                val schedules = listOf(
                    Schedule(
                        id = 1L,
                        title = "Makan Pagi",
                        hour = "2"
                    ),
                    Schedule(
                        id = 2L,
                        title = "Makan Siang",
                        hour = "2"
                    ),
                    Schedule(
                        id = 3L,
                        title = "Makan Malam",
                        hour = "2"
                    )
                )
                ScheduleScreen(
                    onEvent = viewModel::onEvent,
                    schedules = schedules
                )
            }
            composable(route = Route.ClassifyImageScreen.route){
                val viewModel: ClassifyImageViewModel = hiltViewModel()
                ClassifyImageScreen()
            }
        }
    }
}