package com.cyberfeedforward.mycardmanager.ui.screens.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyberfeedforward.mycardmanager.ui.theme.MyCardManagerTheme

@Composable
fun CardsScreen(
    uiState: CardsUiState,
    onAddCardClicked: () -> Unit,
    onRemoveCardClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "Cards",
            style = MaterialTheme.typography.headlineSmall,
        )

        Text(text = "Card count: ${uiState.cardCount}")

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = onRemoveCardClicked) {
                Text(text = "Remove")
            }
            Button(onClick = onAddCardClicked) {
                Text(text = "Add")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardsScreenPreview() {
    MyCardManagerTheme {
        CardsScreen(
            uiState = CardsUiState(cardCount = 12),
            onAddCardClicked = {},
            onRemoveCardClicked = {},
        )
    }
}
