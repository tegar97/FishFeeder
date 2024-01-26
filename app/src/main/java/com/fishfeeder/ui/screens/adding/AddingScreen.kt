package com.fishfeeder.ui.screens.adding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.fishfeeder.ui.screens.adding.component.TimePickerDialog
import com.fishfeeder.ui.screens.navigator.component.FishFeederTopBar
import com.fishfeeder.ui.theme.FishFeederTheme
import com.fishfeeder.ui.theme.spacing
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    val state = rememberTimePickerState()
    var showTimePicker by remember { mutableStateOf(false) }
    var isChooses by remember { mutableStateOf(false) }
    var titleSchedule by remember { mutableStateOf("") }
    val timeResult by remember {
        derivedStateOf {
            if (isChooses) {
                val cal = Calendar.getInstance()
                cal.set(Calendar.HOUR_OF_DAY, state.hour)
                cal.set(Calendar.MINUTE, state.minute)
                cal.isLenient = false
                "${cal.time}"
            } else {
                "Choose Time"
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .padding(MaterialTheme.spacing.small)
        ) {
            AddingComponentForm(
                titleSchedule = titleSchedule,
                showTimePicker = showTimePicker,
                timeResult = timeResult,
                timeState = state,
                onTimePickerClick = {
                    showTimePicker = !showTimePicker
                },
                onTimePickerChoose = {
                    isChooses = true
                    showTimePicker = !showTimePicker
                },
                onTitleChange = {
                    titleSchedule = it
                }
            )
            Spacer(modifier = modifier.height(MaterialTheme.spacing.small))
            Button(
                modifier = modifier
                    .fillMaxWidth(),
                onClick = {}
            ) {
                Text("Simpan")
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
    timeResult: String,
    timeState: TimePickerState,
    onTimePickerClick: () -> Unit,
    onTimePickerChoose: () -> Unit,
    onTitleChange: (String) -> Unit,
) {
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
            timeResult = timeResult,
            stateTime = timeState,
            showTimePicker = showTimePicker,
            onTimePickerClick = onTimePickerClick,
            onTimePickerChoose = onTimePickerChoose
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