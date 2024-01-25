package com.fishfeeder.ui.screens.mainActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import android.Manifest
import android.content.pm.PackageManager

import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.fishfeeder.ui.route.FishFeeder
import com.fishfeeder.ui.screens.classifyImage.CameraPermissions.CAMERAX_PERMISSION
import com.fishfeeder.ui.theme.FishFeederTheme
import com.fishfeeder.ui.theme.spacing
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(
                this, CAMERAX_PERMISSION, 0
            )

        }
        setContent {
            FishFeederTheme {
                FishFeeder()
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

    companion object {
        private val CAMERAX_PERMISSION = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO

        )
    }
}