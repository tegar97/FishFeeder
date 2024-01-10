package com.fishfeeder.ui.screens.navigator.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.fishfeeder.R
import com.fishfeeder.ui.theme.FishFeederTheme
import com.fishfeeder.ui.theme.neutral10

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FishFeederTopBar(
    modifier: Modifier = Modifier,
    title: String,
    isCenteredTitle: Boolean = false,
    hasBackButton: Boolean = false,
    items: List<BottomNavigationItem>? = null,
    onBackClick: (() -> Unit)? = null,
    onItemClick: ((Int) -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = modifier
                    .fillMaxWidth(),
                textAlign = if (isCenteredTitle) TextAlign.Center else TextAlign.Start,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight(600)
                )
            )
        },
        navigationIcon = {
            if (hasBackButton) {
                IconButton(onClick = onBackClick!!) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = null,
                        tint = neutral10
                    )
                }
            }
        },
        actions = {
            items?.forEachIndexed { index, item ->
                IconButton(onClick = {
                    if (onItemClick != null) {
                        onItemClick(index)
                    }
                }) {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null,
                        tint = neutral10
                    )
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = neutral10
        ),
    )
}

data class BottomNavigationItem(
    @DrawableRes val icon: Int
)


@Preview
@Composable
fun ScheduleTopBarPreview() {
    val items = listOf(
        BottomNavigationItem(R.drawable.baseline_alarm_on_24),
        BottomNavigationItem(R.drawable.baseline_restart_alt_24)
    )
    FishFeederTheme {
        FishFeederTopBar(
            title = "FishFeeder",
            items = items,
            onItemClick = {}
        )
    }
}
