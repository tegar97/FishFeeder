package com.fishfeeder.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fishfeeder.R
import com.fishfeeder.ui.theme.FishFeederTheme
import com.fishfeeder.ui.theme.green10
import com.fishfeeder.ui.theme.green40
import com.fishfeeder.ui.theme.poppinsFamily
import com.fishfeeder.ui.theme.red60
import com.fishfeeder.ui.theme.red90
import com.fishfeeder.ui.theme.spacing

@Composable
fun HomeTurbidityAlert(
    modifier: Modifier = Modifier,
    isGood: Boolean,
    ntuValue: String,
) {
    Column(
        modifier = modifier
            .background(if (isGood) green40 else red90)
            .padding(MaterialTheme.spacing.small)
            .fillMaxWidth()
    ) {
        Row {
            Text(
                text = "Kualitas Air",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight(600)
                ),
            )
            Icon(painter = painterResource(id = R.drawable.baseline_water_drop_24), contentDescription = null)
        }
        Spacer(modifier = modifier.height(MaterialTheme.spacing.small))
        Column {
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight(500),
                        fontFamily = poppinsFamily,
                        fontSize = 14.sp
                    )
                ) {
                    append("Tingkat Kekeruhan : ")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppinsFamily,
                        fontSize = 14.sp,
                        color = if (isGood) green10 else red60
                    )
                ) {
                    append(ntuValue)
                    append(" NTU")
                }

            })

            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = if (isGood) "\"Air Bersih, aman untuk ikan\"" else "\"Air kotor, harus segera diganti\"",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight(500),
                    fontFamily = poppinsFamily,
                    fontSize = 14.sp,
                    color = if (isGood) green10 else red60
                )
            )
            Spacer(modifier = modifier.height(MaterialTheme.spacing.small))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeTurbidityAlertPreview() {
    MaterialTheme{
        FishFeederTheme {
            HomeTurbidityAlert(
                isGood = false,
                ntuValue = "999"
            )
        }
    }
}