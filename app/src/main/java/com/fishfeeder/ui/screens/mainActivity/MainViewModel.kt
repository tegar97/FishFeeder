package com.fishfeeder.ui.screens.mainActivity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fishfeeder.ui.screens.navGraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _startDestination = mutableStateOf(Route.FishFeederNavigation.route)
    val startDestination: State<String> = _startDestination

}