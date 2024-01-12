package com.fishfeeder.ui.screens.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.fishfeeder.R
import com.fishfeeder.ui.theme.FishFeederTheme
import com.fishfeeder.ui.theme.neutral10

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onAlarmClick: () -> Unit,
    onRestartClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        title = {
            Text(
                text = title,
                modifier = modifier,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight(600)
                ),
                textAlign = TextAlign.Start
            )
        },
        actions = {
            IconButton(onClick = onAlarmClick) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_alarm_on_24),
                    contentDescription = null,
                    tint = neutral10
                )
            }
            IconButton(onClick = onRestartClick) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_restart_alt_24),
                    contentDescription = null,
                    tint = neutral10
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = neutral10
        )
    )
}

@Preview
@Composable
fun HomeTopBarPrev() {
    FishFeederTheme {
        HomeTopBar(
            title = "FishFeeder",
            onAlarmClick = {},
            onRestartClick = {}
        )
    }
}