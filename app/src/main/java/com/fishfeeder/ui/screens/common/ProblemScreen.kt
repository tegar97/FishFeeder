package com.fishfeeder.ui.screens.common

import android.os.Build.DEVICE
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.fishfeeder.R
import com.fishfeeder.ui.theme.FishFeederTheme
import com.fishfeeder.ui.theme.spacing

@Composable
fun ProblemScreen(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes drawable: Int,
    buttonText: String,
    navigate: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize(0.8f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier,
            painter = painterResource(id = drawable),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = modifier.height(MaterialTheme.spacing.small))
        Text(
            modifier = modifier
                .fillMaxWidth(),
            text = text,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight(600)
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.height(MaterialTheme.spacing.small))
        Button(
            modifier = modifier
                .fillMaxWidth(),
            onClick = navigate
        ) {
            Text(text = buttonText)
        }
    }
}


@Preview(showBackground = true, device = Devices.DEFAULT)
@Composable
fun ProblemScreenPreview() {
    FishFeederTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProblemScreen(
                modifier = Modifier,
                text = "Tidak ada jadwal yang disetting",
                drawable = R.drawable.server_problem,
                buttonText = "Tambah jadwal makanan"
            ) {

            }
        }
    }
}