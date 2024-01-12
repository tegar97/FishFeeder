package com.fishfeeder.ui.screens.adding.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.fishfeeder.R
import com.fishfeeder.ui.theme.FishFeederTheme
import com.fishfeeder.ui.theme.neutral10
import com.fishfeeder.ui.theme.neutral80
import com.fishfeeder.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    modifier: Modifier = Modifier,
    timeResult: String,
    stateTime: TimePickerState,
    showTimePicker: Boolean,
    onTimePickerClick: () -> Unit,
    onTimePickerChoose: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = modifier
                .weight(9f),
            value = timeResult,
            onValueChange = {},
            label = {},
            singleLine = true,
            readOnly = true
        )
        Spacer(modifier = modifier.width(MaterialTheme.spacing.small))
        Button(
            modifier = modifier
                .weight(2f),
            onClick = onTimePickerClick,
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_alarm_on_24),
                contentDescription = null
            )
        }
        if (showTimePicker) {
            TimePickerDialog(
                onCancel = onTimePickerClick,
                onConfirm = onTimePickerChoose,
            ) {
                TimePicker(state = stateTime)
            }
        }
    }
}

@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    toggle: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onCancel,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = neutral80
                ),
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    toggle()
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        colors = ButtonDefaults.buttonColors(contentColor = neutral10),
                        onClick = onCancel
                    ) { Text("Cancel") }

                    TextButton(
                        colors = ButtonDefaults.buttonColors(contentColor = neutral10),
                        onClick = onConfirm
                    ) { Text("OK") }
                }
            }
        }
    }
}

@Preview
@Composable
fun TimePickerTextFieldPreview() {

}