package com.cyberfeedforward.mycardmanager.ui.cards

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import java.io.File

@Composable
fun CardsRoute(
    viewModel: CardsViewModel = viewModel(),
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val storage = remember {
        ScanHistoryStorage(
            file = File(context.filesDir, "scanned_codes.json"),
        )
    }
    var savedScans by remember { mutableStateOf(emptyList<ScanHistoryStorage.SavedScan>()) }

    var editingIndex by remember { mutableStateOf<Int?>(null) }
    var editingName by rememberSaveable { mutableStateOf("") }

    var pendingDeleteIndex by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(Unit) {
        savedScans = storage.readAll()
    }

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCameraPermission = granted
            if (granted) {
                viewModel.onScanRequested()
            } else {
                viewModel.onScanError("Camera permission denied")
            }
        },
    )

    CardsScreen(
        uiState = uiState,
        savedScans = savedScans,
        onAddCard = viewModel::onAddCard,
        onRemoveCard = viewModel::onRemoveCard,
        onScan = {
            if (hasCameraPermission) {
                viewModel.onScanRequested()
            } else {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        },
        onEditScan = { index ->
            val scan = savedScans.getOrNull(index) ?: return@CardsScreen
            editingIndex = index
            editingName = scan.name
        },
        onDeleteScan = { index ->
            if (savedScans.getOrNull(index) != null) {
                pendingDeleteIndex = index
            }
        },
    )

    if (pendingDeleteIndex != null) {
        AlertDialog(
            onDismissRequest = {
                pendingDeleteIndex = null
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val index = pendingDeleteIndex ?: return@TextButton
                        val deleted = storage.deleteAt(index)
                        if (deleted) {
                            savedScans = storage.readAll()
                        }
                        pendingDeleteIndex = null
                    },
                ) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        pendingDeleteIndex = null
                    },
                ) {
                    Text(text = "No")
                }
            },
            title = {
                Text(text = "Delete")
            },
            text = {
                Text(text = "Are you sure you want to delete this card?")
            },
        )
    }

    if (editingIndex != null) {
        AlertDialog(
            onDismissRequest = {
                editingIndex = null
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val index = editingIndex ?: return@TextButton
                        val scan = savedScans.getOrNull(index) ?: run {
                            editingIndex = null
                            return@TextButton
                        }

                        val updated = storage.updateAt(
                            index = index,
                            scan = scan.copy(name = editingName),
                        )
                        if (updated) {
                            savedScans = storage.readAll()
                        }
                        editingIndex = null
                    }
                ) {
                    Text(text = "Save")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        editingIndex = null
                    }
                ) {
                    Text(text = "Cancel")
                }
            },
            title = {
                Text(text = "Edit")
            },
            text = {
                OutlinedTextField(
                    value = editingName,
                    onValueChange = { editingName = it },
                    label = { Text(text = "Card Name") },
                    singleLine = true,
                )
            },
        )
    }

    if (uiState.isScannerVisible) {
        BarcodeScannerDialog(
            onBarcodeScanned = viewModel::onBarcodeScanned,
            onDismiss = viewModel::onScannerDismissed,
            onError = viewModel::onScanError,
        )
    }

    val scanResult = uiState.scanResult
    if (scanResult != null) {
        when (scanResult) {
            is ScanResultUi.Success -> {
                ScanResultDialog(
                    viewModel = viewModel,
                    message = scanResult.value,
                    type = scanResult.type,
                    onSaved = {
                        savedScans = storage.readAll()
                    },
                )
            }
            is ScanResultUi.Error -> {
                ScanFailDialog(viewModel, scanResult.message)
            }
        }
    }
}

@Composable
private fun ScanResultDialog(
    viewModel: CardsViewModel,
    message: String,
    type: ScannedCodeType,
    onSaved: () -> Unit,
) {
    val context = LocalContext.current
    var cardName by rememberSaveable { mutableStateOf("") }

    val codeBitmap = remember(message, type) {
        if (type.isQr) {
            CodeImageGenerator.generateQrBitmap(
                value = message,
                sizePx = 512,
            )
        } else {
            CodeImageGenerator.generateBarcodeBitmap(
                value = message,
                widthPx = 768,
                heightPx = 256,
            )
        }
    }

    AlertDialog(
        onDismissRequest = viewModel::onScanResultDismissed,
        confirmButton = {
            TextButton(
                onClick = {
                    val storage = ScanHistoryStorage(
                        file = File(context.filesDir, "scanned_codes.json"),
                    )
                    storage.append(
                        ScanHistoryStorage.SavedScan(
                            name = cardName,
                            code = message,
                            type = type,
                        )
                    )
                    onSaved()
                    viewModel.onScanResultDismissed()
                },
            ) {
                Text(text = "Save")
            }
        },
        title = {
            Text(
                text = "Scanned code",
                fontSize = 25.sp
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Image(
                    bitmap = codeBitmap.asImageBitmap(),
                    contentDescription = "Scanned code",
                    modifier = Modifier //.size(220.dp),
                )

                Text(
                    text = message,
                    fontSize = 20.sp
                    )

                OutlinedTextField(
                    value = cardName,
                    onValueChange = { cardName = it },
                    label = { Text(text = "Card Name") },
                    placeholder = { Text(text = "What is your Card Name?") },
                    singleLine = true,
                )

                Text(text = "Type: ${type.label}")
            }
        },
    )
}

@Composable
private fun ScanFailDialog(
    viewModel: CardsViewModel,
    message: String
) {
    AlertDialog(
        onDismissRequest = viewModel::onScanResultDismissed,
        confirmButton = {
            TextButton(onClick = viewModel::onScanResultDismissed) {
                Text(text = "OK")
            }
        },
        title = {
            Text(
                text = "Scan failed"
            )
        },
        text = {
            Text(text = message)
        },
    )
}
