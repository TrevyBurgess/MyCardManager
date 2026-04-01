package com.cyberfeedforward.mycardmanager.ui.screens.cards

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.cyberfeedforward.mycardmanager.ui.theme.MyCardManagerTheme

@Composable
fun CardsScreen(
    uiState: CardsUiState,
    onAddCardClicked: () -> Unit,
    onRemoveCardClicked: () -> Unit,
    onNewCardClicked: () -> Unit,
    onScannerDismissed: () -> Unit,
    onBarcodeScanned: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            if (!granted) {
                onBarcodeScanned("Camera permission denied")
            }
        },
    )

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
            Button(onClick = onNewCardClicked) {
                Text(text = "NewCard")
            }
        }
    }

    if (uiState.isScannerVisible) {
        AlertDialog(
            onDismissRequest = onScannerDismissed,
            confirmButton = {
                Button(onClick = onScannerDismissed) {
                    Text(text = "Close")
                }
            },
            title = { Text(text = "Scan code") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        Button(onClick = { requestPermissionLauncher.launch(Manifest.permission.CAMERA) }) {
                            Text(text = "Grant camera permission")
                        }
                    } else {
                        BarcodeScannerView(
                            onBarcodeScanned = onBarcodeScanned,
                        )
                    }

                    Text(text = uiState.lastScannedCode ?: "No code scanned yet")
                }
            },
        )
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
            onNewCardClicked = {},
            onScannerDismissed = {},
            onBarcodeScanned = {},
        )
    }
}
