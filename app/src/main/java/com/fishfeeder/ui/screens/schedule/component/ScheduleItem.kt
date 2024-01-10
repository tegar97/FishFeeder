package com.fishfeeder.ui.screens.schedule.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.fishfeeder.ui.theme.FishFeederTheme

@Composable
fun ScheduleItem(
    modifier : Modifier = Modifier,
    title : String,
    checked : Boolean,
    onCheckedClick : (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight(500)
            )
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedClick
        )
    }
}

@Preview
@Composable
fun ScheduleItemPreview() {
    FishFeederTheme {
        var checked by remember { mutableStateOf(true) }

        Surface {
            ScheduleItem(
                title = "Makan Pagi",
                checked = checked,
                onCheckedClick = {
                    checked = it
                }
            )
        }
    }
}

