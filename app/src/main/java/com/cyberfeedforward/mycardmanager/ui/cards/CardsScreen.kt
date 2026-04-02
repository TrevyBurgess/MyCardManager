package com.cyberfeedforward.mycardmanager.ui.cards

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
fun CardsScreen(
    uiState: CardsUiState,
    onAddCard: () -> Unit,
    onRemoveCard: () -> Unit,
    onScan: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Cards: ${uiState.cardCount}",
            style = MaterialTheme.typography.headlineSmall,
        )

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = onAddCard) {
                Text(text = "Add")
            }
            Button(onClick = onRemoveCard) {
                Text(text = "Remove")
            }
            Button(onClick = onScan) {
                Text(text = "Scan")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardsScreenPreview() {
    MyCardManagerTheme {
        CardsScreen(
            uiState = CardsUiState(
                cardCount = 3,
                isScannerVisible = false,
                scanResult = null,
            ),
            onAddCard = {},
            onRemoveCard = {},
            onScan = {},
        )
    }
}
