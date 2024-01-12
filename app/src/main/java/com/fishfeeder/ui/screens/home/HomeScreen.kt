package com.fishfeeder.ui.screens.home

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fishfeeder.R
import com.fishfeeder.domain.model.History
import com.fishfeeder.ui.screens.home.components.HistoryCard
import com.fishfeeder.ui.screens.navigator.component.BottomNavigationItem
import com.fishfeeder.ui.screens.navigator.component.FishFeederTopBar
import com.fishfeeder.ui.theme.FishFeederTheme
import com.fishfeeder.ui.theme.neutral60
import com.fishfeeder.ui.theme.spacing


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    itemsNavigation: List<BottomNavigationItem>,
    onNavigationItemClick: (Int) -> Unit
) {
    val histories = listOf(
        History(id = 1, title = "Makan siang", hour = "2", status = true),
        History(id = 2, title = "Makan malam", hour = "19", status = false),
        History(id = 3, title = "Makan pagi", hour = "7", status = true),
    )
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        FishFeederTopBar(
            title = "FishFeeder",
            items = itemsNavigation,
            onItemClick = onNavigationItemClick
        )
        HomeComponentTimer(
            modifier = modifier,
            taskTitle = "Makan Siang",
            timer = "01:00:00",
        )
        Spacer(
            modifier = modifier.height(MaterialTheme.spacing.medium)
        )
        HomeComponentHistories(modifier = modifier, histories = histories)
    }
}

@Composable
fun HomeComponentTimer(
    modifier: Modifier = Modifier,
    taskTitle: String,
    timer: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Makan Selanjutnya",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight(500)
            )
        )
        Spacer(
            modifier = modifier.height(6.dp)
        )
        Text(
            text = taskTitle,
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
            text = "History",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight(600)
            )
        )
        LazyColumn {
            items(items = histories, key = { it.id }) {
                HistoryCard(
                    success = it.status,
                    time = "${it.hour} jam yang lalu"
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
            History(id = 1, title = "Makan siang", hour = "2", status = true),
            History(id = 2, title = "Makan malam", hour = "19", status = false),
            History(id = 3, title = "Makan pagi", hour = "7", status = true),
        )
        val items = listOf(
            BottomNavigationItem(R.drawable.baseline_alarm_on_24),
            BottomNavigationItem(R.drawable.baseline_restart_alt_24)
        )
        HomeScreen(
            itemsNavigation = items,
            onNavigationItemClick = {}
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun HomeComponentTimerPreview() {
    FishFeederTheme {
        HomeComponentTimer(
            taskTitle = "Makan Siang",
            timer = "02:00:00"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeComponentHistoriesPreview() {
    FishFeederTheme {
        val histories = listOf(
            History(id = 1, title = "Makan siang", hour = "2", status = true),
            History(id = 2, title = "Makan malam", hour = "19", status = false),
            History(id = 3, title = "Makan pagi", hour = "7", status = true),
        )
        HomeComponentHistories(histories = histories)
    }
}