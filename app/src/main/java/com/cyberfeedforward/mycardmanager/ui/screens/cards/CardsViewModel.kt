package com.cyberfeedforward.mycardmanager.ui.screens.cards

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class CardsUiState(
    val cardCount: Int = 0,
    val isScannerVisible: Boolean = false,
    val lastScannedCode: String? = null,
)

class CardsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CardsUiState())
    val uiState: StateFlow<CardsUiState> = _uiState

    fun onAddCardClicked() {
        _uiState.update { it.copy(cardCount = it.cardCount + 1) }
    }

    fun onRemoveCardClicked() {
        _uiState.update { current ->
            current.copy(cardCount = (current.cardCount - 1).coerceAtLeast(0))
        }
    }

    fun onNewCardClicked() {
        _uiState.update { it.copy(isScannerVisible = true, lastScannedCode = null) }
    }

    fun onScannerDismissed() {
        _uiState.update { it.copy(isScannerVisible = false) }
    }

    fun onBarcodeScanned(value: String) {
        if (value.isBlank()) return

        _uiState.update { current ->
            current.copy(lastScannedCode = value)
        }
    }
}
