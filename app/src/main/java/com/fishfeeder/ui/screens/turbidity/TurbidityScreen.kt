package com.fishfeeder.ui.screens.turbidity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.fishfeeder.R
import com.fishfeeder.ui.theme.FishFeederTheme
import com.fishfeeder.ui.theme.spacing

@Composable
fun TurbidityScreen(
    modifier: Modifier = Modifier,
    onEvent: (TurbidityEvent) -> Unit,
) {
    var ntuValue by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.small)
    ) {
        Spacer(modifier = modifier.height(MaterialTheme.spacing.small))
        Text(
            text = stringResource(R.string.title_screen_turbidity),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight(500)
            )
        )
        Spacer(modifier = modifier.height(MaterialTheme.spacing.medium))
        Text(
            text = stringResource(R.string.label_turbidity_screen_ntu_value),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight(400)
            )
        )
        Spacer(modifier = modifier.height(MaterialTheme.spacing.small))
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth(),
            value = ntuValue,
            onValueChange = {
                ntuValue = it
            },
            placeholder = {
                Text(text = "Range nilai 1 sampai 99")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = modifier.height(MaterialTheme.spacing.medium))
        Button(
            modifier = modifier
                .fillMaxWidth(),
            onClick = {

            }
        ) {
            Text(stringResource(R.string.label_adding_screen_save))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TurbidityScreenPreview() {
    MaterialTheme {
        FishFeederTheme {
            TurbidityScreen(
                onEvent = {

                }
            )
        }
    }
}