package com.fishfeeder.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fishfeeder.ui.theme.FishFeederTheme
import com.fishfeeder.ui.theme.green10
import com.fishfeeder.ui.theme.poppinsFamily
import com.fishfeeder.ui.theme.red60
import com.fishfeeder.ui.theme.spacing

@Composable
fun HistoryCard(
    title: String,
    time: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.small),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = title)
        Spacer(modifier = modifier.height(10.dp))
        Text(text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Normal,
                    fontFamily = poppinsFamily,
                    fontSize = 14.sp
                )
            ) {
                append("Waktu : ")
            }

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight(700),
                    fontFamily = poppinsFamily,
                    fontSize = 14.sp
                )
            ) {
                append(time)
            }
        })
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleCardPrev() {
    MaterialTheme {
        FishFeederTheme {
            HistoryCard(title = "Makan Mukbang", "15:00")
        }
    }
}

