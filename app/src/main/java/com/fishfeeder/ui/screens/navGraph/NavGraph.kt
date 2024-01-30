package com.fishfeeder.ui.screens.navGraph

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fishfeeder.R
import com.fishfeeder.data.local.entity.ScheduleEntity
import com.fishfeeder.data.local.util.UiState
import com.fishfeeder.domain.model.History
import com.fishfeeder.domain.model.Schedule
import com.fishfeeder.ui.screens.adding.AddingScreen
import com.fishfeeder.ui.screens.adding.AddingViewModel
import com.fishfeeder.ui.screens.classifyImage.ClassifyImageScreen
import com.fishfeeder.ui.screens.classifyImage.ClassifyImageViewModel
import com.fishfeeder.ui.screens.common.ProblemScreen
import com.fishfeeder.ui.screens.common.ProblemScreenPreview
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
                        BottomNavigationItem(R.drawable.baseline_camera_alt_24),
                        BottomNavigationItem(R.drawable.baseline_alarm_on_24),
                        BottomNavigationItem(R.drawable.baseline_water_drop_24)
                    )
                    FishFeederTopBar(
                        title = "FishFeeder",
                        items = items,
                        onItemClick = {
                            when (it) {
                                0 -> navController.navigate(Route.ClassifyImageScreen.route)
                                1 -> navController.navigate(Route.ListScheduleScreen.route)
                                2 -> navController.navigate(Route.TurbidityScreen.route)
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
                val nearScheduleState by viewModel.scheduleState.collectAsState()
                DisposableEffect(key1 = viewModel) {
                    viewModel.getNearestSchedule()
                    onDispose {
                        viewModel.resetTimer()
                    }
                }

                val schedule: ScheduleEntity? = when (val state = nearScheduleState) {
                    is UiState.Success -> {

                        LaunchedEffect(state.data) {
                            viewModel.startTimer(state.data.hour)
                        }

                        state.data
                    }
                    is UiState.Error -> {

                        null
                    }
                    is UiState.Loading -> {

                        null
                    }
                    else -> null
                }

                val histories = listOf(
                    History(id = 1, title = "Makan siang", hour = "02:00"),
                    History(id = 2, title = "Makan malam", hour = "19:00"),
                    History(id = 3, title = "Makan pagi", hour = "17:00"),
                )
                val countdownTimer = viewModel.currentTimeString
                if (schedule != null) {
                    HomeScreen(
                        nearSchedule = schedule,
                        countdownTimer = countdownTimer,
                        histories = histories,
                        onEvent = viewModel::onEvent
                    )
                }else{
                    ProblemScreen(
                        modifier = Modifier,
                        text = "Tidak ada jadwal yang disetting",
                        drawable = R.drawable.calendar_empty,
                        buttonText = "Tambah jadwal makanan",
                        navigate = {
                            navController.navigate( Route.AddingScheduleScreen.route)
                        }
                    )
                }
            }
            composable(route = Route.AddingScheduleScreen.route) {
                val viewModel: AddingViewModel = hiltViewModel()
                AddingScreen(onNavigateBack = {navController.navigateUp()}, onEvent = viewModel::onEvent)
            }
            composable(route = Route.ListScheduleScreen.route) {
                val viewModel: ScheduleViewModel = hiltViewModel()
                val scheduleState by viewModel.scheduleState.collectAsState()
                DisposableEffect(key1 = viewModel) {
                    viewModel.fetchAllSchedule()
                    onDispose {}
                }



                val  schedules = if (scheduleState is UiState.Success) {
                    (scheduleState as UiState.Success<List<ScheduleEntity>>).data.map { scheduleEntity ->
                        Schedule(
                            id = scheduleEntity.id,
                            title = scheduleEntity.title,
                            hour = scheduleEntity.hour,
                            status  = scheduleEntity.status
                        )
                    }
                } else {
                    emptyList() // or provide a default list or handle loading/error state accordingly
                }
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