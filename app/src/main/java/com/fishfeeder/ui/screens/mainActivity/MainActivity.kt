package com.fishfeeder.ui.screens.mainActivity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.fishfeeder.R
import com.fishfeeder.domain.MqttStatus
import com.fishfeeder.services.MqttService
import com.fishfeeder.ui.screens.common.ProblemScreen
import com.fishfeeder.ui.screens.navGraph.NavGraph
import com.fishfeeder.ui.screens.navGraph.Route
import com.fishfeeder.ui.theme.FishFeederTheme
import com.fishfeeder.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(
                this, CAMERAX_PERMISSION, 0
            )
        }
        setContent {
            FishFeederTheme {
                val viewModel: MainViewModel = hiltViewModel()
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var isActive by remember {
                        mutableStateOf(false)
                    }
                    viewModel.serviceConnected.collectAsState(initial = MqttStatus.Disconnected).value.let { serviceActive ->
                        isActive = when (serviceActive) {
                            is MqttStatus.Connected -> {
                                true
                            }

                            is MqttStatus.Disconnected -> {
                                false
                            }

                            is MqttStatus.Error -> {
                                Log.e("TAG", "onCreate: ${serviceActive.error}", )
                                false
                            }
                        }
                        if (!isActive) {
                            ProblemScreen(
                                modifier = Modifier,
                                text = "Maaf terjadi masalah koneksi",
                                drawable = R.drawable.server_problem,
                                buttonText = "Coba Hubungkan Kembali"
                            ) {
                                connectToService()
                            }

//                            NavGraph(startDestination = Route.HomeScreen.route)

                        } else {
                            NavGraph(startDestination = Route.HomeScreen.route)
                        }
                    }
                }
            }
        }
    }

    private fun hasRequiredPermissions(): Boolean {
        return CAMERAX_PERMISSION.all {
            ContextCompat.checkSelfPermission(
                applicationContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun connectToService() {
        Intent(this, MqttService::class.java).let {
            it.action = Constants.ACTION_START_SERVICE
            startService(it)
        }
    }

    companion object {
        private val CAMERAX_PERMISSION = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO

        )
    }
}