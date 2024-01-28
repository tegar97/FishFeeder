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
import com.fishfeeder.R
import com.fishfeeder.domain.model.History
import com.fishfeeder.domain.model.Schedule
import com.fishfeeder.ui.screens.adding.AddingScreen
import com.fishfeeder.ui.screens.adding.AddingViewModel
import com.fishfeeder.ui.screens.classifyImage.ClassifyImageScreen
import com.fishfeeder.ui.screens.classifyImage.ClassifyImageViewModel
import com.fishfeeder.ui.screens.home.HomeScreen
import com.fishfeeder.ui.screens.home.HomeViewModel
import com.fishfeeder.ui.screens.navigator.BottomNavigationItem
import com.fishfeeder.ui.screens.navigator.FishFeederTopBar
import com.fishfeeder.ui.screens.schedule.ScheduleScreen
import com.fishfeeder.ui.screens.schedule.ScheduleViewModel
import com.fishfeeder.ui.screens.turbidity.TurbidityScreen
import com.fishfeeder.ui.screens.turbidity.TurbidityViewModel

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
        modifier = modifier,
        topBar = {
            when (currentRoute) {
                Route.HomeScreen.route -> {
                    val items = listOf(
                        BottomNavigationItem(R.drawable.baseline_alarm_on_24),
                        BottomNavigationItem(R.drawable.baseline_water_drop_24)
                    )
                    FishFeederTopBar(
                        title = "FishFeeder",
                        items = items,
                        onItemClick = {
                            when (it) {
                                0 -> navController.navigate(Route.ListScheduleScreen.route)
                                1 -> navController.navigate(Route.TurbidityScreen.route)
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

                Route.TurbidityScreen.route -> {
                    FishFeederTopBar(
                        title = "Batas Keruh Ikan",
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
                    History(id = 1, title = "Makan siang", hour = "02:00"),
                    History(id = 2, title = "Makan malam", hour = "19:00"),
                    History(id = 3, title = "Makan pagi", hour = "17:00"),
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
            composable(route = Route.TurbidityScreen.route) {
                val viewModel: TurbidityViewModel = hiltViewModel()
                TurbidityScreen(onEvent = viewModel::onEvent)
            }
        }
    }
}