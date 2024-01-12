package com.fishfeeder.ui.screens.adding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.fishfeeder.ui.screens.adding.component.TimePickerDialog
import com.fishfeeder.ui.screens.navigator.component.FishFeederTopBar
import com.fishfeeder.ui.theme.FishFeederTheme
import com.fishfeeder.ui.theme.neutral10
import com.fishfeeder.ui.theme.neutral80
import com.fishfeeder.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    var selectedHour by remember { mutableStateOf("00") }
    var selectedMinute by remember { mutableStateOf("00") }
    var showTimePicker by remember { mutableStateOf(false) }
    var titleSchedule by remember { mutableStateOf("") }

    Column {
        FishFeederTopBar(
            title = "Jadwal Makan",
            hasBackButton = true,
            isCenteredTitle = true,
            onBackClick = onBackClick
        )
        Column(
            modifier = modifier
                .padding(MaterialTheme.spacing.small)
        ) {
            AddingComponentForm(
                titleSchedule = titleSchedule,
                showTimePicker = showTimePicker,
                onTimePickerClick = {
                    showTimePicker = !showTimePicker
                },
                onTitleChange = {
                    titleSchedule = it
                }
            )
            Spacer(modifier = modifier.height(MaterialTheme.spacing.small))
            Button(
                modifier = modifier
                    .fillMaxSize(),
                onClick = {}
            ) {
                Text("15/12/2024")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingComponentForm(
    modifier: Modifier = Modifier,
    titleSchedule: String,
    showTimePicker: Boolean,
    onTimePickerClick: () -> Unit,
    onTitleChange: (String) -> Unit,
) {
    val state = rememberTimePickerState()
    Column {
        Text(
            text = "List Jadwal Makan Ikan",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight(600)
            )
        )
        Spacer(modifier = modifier.height(MaterialTheme.spacing.medium))
        Text(
            text = "Nama Jadwal",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight(500)
            )
        )
        Spacer(modifier = modifier.height(MaterialTheme.spacing.medium))
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth(),
            value = titleSchedule,
            onValueChange = onTitleChange,
            placeholder = {
                Text(text = "Contoh Makan Pagi")
            }
        )
        Spacer(modifier = modifier.height(MaterialTheme.spacing.medium))
        Text(
            text = "Waktu",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight(500)
            )
        )
        Spacer(modifier = modifier.height(MaterialTheme.spacing.medium))
        TimePickerDialog(
            timeResult = "",
            stateTime = state,
            showTimePicker = showTimePicker,
            onTimePickerClick = onTimePickerClick
        )
    }
}



@Preview(showBackground = true)
@Composable
fun AddingScreenPreview() {
    FishFeederTheme {
        AddingScreen(
            onBackClick = {

            }
        )
    }
}