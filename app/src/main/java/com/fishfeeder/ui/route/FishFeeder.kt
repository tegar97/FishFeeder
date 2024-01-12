package com.fishfeeder.ui.route

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fishfeeder.R
import com.fishfeeder.ui.screens.adding.AddingScreen
import com.fishfeeder.ui.screens.home.HomeScreen
import com.fishfeeder.ui.screens.navigator.component.BottomNavigationItem
import com.fishfeeder.ui.screens.schedule.ScheduleScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FishFeeder(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold() { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Route.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Route.Home.route) {
                HomeScreen(
                    itemsNavigation = AppBarAction.HomeAppBar,
                    onNavigationItemClick = {it ->
                        navController.navigate(Route.Schedule.route)

                    }
                )


            }

            composable(Route.Schedule.route) {
                ScheduleScreen(
                    itemsNavigation = AppBarAction.ScheduleAppBar,
                    onNavigationItemClick = {
                        navController.navigate(Route.Add.route)
                    },
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(Route.Add.route) {
                AddingScreen(

                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }


}