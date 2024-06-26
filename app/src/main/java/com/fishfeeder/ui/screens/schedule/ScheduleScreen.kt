package com.fishfeeder.ui.screens.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.fishfeeder.R
import com.fishfeeder.domain.model.Schedule
import com.fishfeeder.ui.screens.schedule.component.ScheduleItem
import com.fishfeeder.ui.theme.FishFeederTheme
import com.fishfeeder.ui.theme.spacing

@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    schedules: List<Schedule> = emptyList(),
    onEvent: (ScheduleEvent) -> Unit
) {
    val switches = remember { mutableStateMapOf<Long, Boolean>() }

    Column(
        modifier = modifier
    ) {
        ScheduleComponentSchedules(
            modifier = modifier
                .padding(MaterialTheme.spacing.small),
            schedules = schedules,
            onCheckedClick = { value, key ->
                switches[key] = value
                onEvent(ScheduleEvent.OnChangeSchedule(id = key, isActive = value))

            },

            switchState = switches
        )
    }
}

@Composable
fun ScheduleComponentSchedules(
    modifier: Modifier = Modifier,
    schedules: List<Schedule>,
    onCheckedClick: (Boolean, Long) -> Unit,
    switchState: SnapshotStateMap<Long, Boolean>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.title_screen_schedule),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight(600)
            )
        )
        LazyColumn {
            items(items = schedules, key = { it.id }) {
                ScheduleItem(
                    title = it.title,
                    checked = switchState[it.id] ?: it.status,
                    onCheckedClick = { value ->
                        onCheckedClick(value, it.id)

                    }
                )
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun ScheduleComponentSchedulesPreview() {
    val switches = remember { mutableStateMapOf<Long, Boolean>() }

    val schedules = listOf(
        Schedule(
            id = 1L,
            title = "Makan Pagi",
            hour = "2",
            status = false,
        ),
        Schedule(
            id = 2L,
            title = "Makan Siang",
            hour = "2",
            status = false,

            ),
        Schedule(
            id = 3L,
            title = "Makan Malam",
            hour = "2",
            status = false,

            ),
    )
    FishFeederTheme {
        ScheduleComponentSchedules(
            schedules = schedules,
            onCheckedClick = { value, key ->
                switches[key] = value
            },
            switchState = switches
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleScreenPreview() {
    val schedules = listOf(
        Schedule(
            id = 1L,
            title = "Makan Pagi",
            hour = "2",
            status = false,

            ),
        Schedule(
            id = 2L,
            title = "Makan Siang",
            hour = "2",
            status = false,

            ),
        Schedule(
            id = 3L,
            title = "Makan Malam",
            hour = "2",
            status = false,

            ),
    )

    FishFeederTheme {
        ScheduleScreen(
            schedules = schedules,
            onEvent = {

            }
        )
    }
}