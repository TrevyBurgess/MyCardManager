package com.cyberfeedforward.mycardmanager.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyberfeedforward.mycardmanager.ui.theme.MyCardManagerTheme

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onIncrementClicked: () -> Unit,
    onDecrementClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = uiState.title,
            style = MaterialTheme.typography.headlineSmall,
        )

        Text(text = "Counter: ${uiState.counter}")

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = onDecrementClicked) {
                Text(text = "-1")
            }
            Button(onClick = onIncrementClicked) {
                Text(text = "+1")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    MyCardManagerTheme {
        HomeScreen(
            uiState = HomeUiState(title = "MyCardManager", counter = 3),
            onIncrementClicked = {},
            onDecrementClicked = {},
        )
    }
}
