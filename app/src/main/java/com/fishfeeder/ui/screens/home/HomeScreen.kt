package com.fishfeeder.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fishfeeder.R
import com.fishfeeder.data.local.entity.ScheduleEntity
import com.fishfeeder.domain.model.History
import com.fishfeeder.ui.screens.home.components.HistoryCard
import com.fishfeeder.ui.screens.home.components.HomeTurbidityAlert
import com.fishfeeder.ui.theme.FishFeederTheme
import com.fishfeeder.ui.theme.neutral60
import com.fishfeeder.ui.theme.spacing
import org.jetbrains.annotations.Async.Schedule


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    countdownTimer : String,
    nearSchedule : ScheduleEntity,
    histories: List<History> = emptyList(),
    isGood: Boolean?,
    ntuValue: String?,
    onEvent: (HomeEvent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        HomeComponentTimer(
            modifier = modifier
                .clickable {
                    onEvent(HomeEvent.CallServo)
                },
            taskTitle = nearSchedule.title,
            taskTime = nearSchedule.hour,
            timer = countdownTimer,
        )
        Spacer(
            modifier = modifier.height(MaterialTheme.spacing.small)
        )
        HomeTurbidityAlert(isGood = isGood?:false, ntuValue = ntuValue?:"")
        HomeComponentHistories(modifier = modifier, histories = histories)
    }
}

@Composable
fun HomeComponentTimer(
    modifier: Modifier = Modifier,
    taskTitle: String,
    taskTime : String,
    timer: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.header_screen_home_next_schedule),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight(500)
            )
        )
        Spacer(
            modifier = modifier.height(6.dp)
        )
        Text(
            text =  "$taskTitle ($taskTime)",
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight(400)
            ),
            color = neutral60
        )
        Column(
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = modifier.height(18.dp))
            Text(
                text = timer,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight(700)
                )
            )
        }
    }
}

@Composable
fun HomeComponentHistories(
    modifier: Modifier = Modifier,
    histories: List<History>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small),
    ) {
        Text(
            text = "All Schedule",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight(600)
            )
        )
        LazyColumn {
            items(items = histories, key = { it.id }) {
                HistoryCard(
                    title = it.title,
                    time = it.hour
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun HomScreenPreview() {
    FishFeederTheme {
        val histories = listOf(
            History(id = 1, title = "Makan siang", hour = "02:00"),
            History(id = 2, title = "Makan malam", hour = "19:00"),
            History(id = 3, title = "Makan pagi", hour = "17:00"),
        )
        val dummySchedule = ScheduleEntity(0,"Makang Siang","15:00",false)
        HomeScreen(
            onEvent = {

            },
            countdownTimer = "01:00:00",
            nearSchedule = dummySchedule,
            histories = histories,
            isGood = true,
            ntuValue = "999"
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun HomeComponentTimerPreview() {
    FishFeederTheme {
        HomeComponentTimer(
            taskTitle = "Makan Siang",
            timer = "02:00:00",
            taskTime = "16:50"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeComponentHistoriesPreview() {
    FishFeederTheme {
        val histories = listOf(
            History(id = 1, title = "Makan siang", hour = "02:00"),
            History(id = 2, title = "Makan malam", hour = "19:00"),
            History(id = 3, title = "Makan pagi", hour = "17:00"),
        )
        HomeComponentHistories(histories = histories)
    }
}