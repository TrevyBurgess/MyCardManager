package com.cyberfeedforward.mycardmanager

import com.cyberfeedforward.mycardmanager.ui.screens.cards.CardsViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class CardsViewModelTest {
    @Test
    fun addCard_increasesCount() {
        val viewModel = CardsViewModel()

        viewModel.onAddCardClicked()

        assertEquals(1, viewModel.uiState.value.cardCount)
    }

    @Test
    fun removeCard_atZero_staysAtZero() {
        val viewModel = CardsViewModel()

        viewModel.onRemoveCardClicked()

        assertEquals(0, viewModel.uiState.value.cardCount)
    }

    @Test
    fun removeCard_afterAdds_decreasesCount() {
        val viewModel = CardsViewModel()

        viewModel.onAddCardClicked()
        viewModel.onAddCardClicked()
        viewModel.onRemoveCardClicked()

        assertEquals(1, viewModel.uiState.value.cardCount)
    }

    @Test
    fun newCard_opensScannerAndClearsLastScanned() {
        val viewModel = CardsViewModel()

        viewModel.onBarcodeScanned("123")
        viewModel.onNewCardClicked()

        assertEquals(true, viewModel.uiState.value.isScannerVisible)
        assertEquals(null, viewModel.uiState.value.lastScannedCode)
    }

    @Test
    fun dismissScanner_closesScanner() {
        val viewModel = CardsViewModel()

        viewModel.onNewCardClicked()
        viewModel.onScannerDismissed()

        assertEquals(false, viewModel.uiState.value.isScannerVisible)
    }

    @Test
    fun barcodeScanned_blankValue_doesNotChangeLastScanned() {
        val viewModel = CardsViewModel()

        viewModel.onBarcodeScanned("   ")

        assertEquals(null, viewModel.uiState.value.lastScannedCode)
    }

    @Test
    fun barcodeScanned_validValue_updatesLastScanned() {
        val viewModel = CardsViewModel()

        viewModel.onBarcodeScanned("ABC-123")

        assertEquals("ABC-123", viewModel.uiState.value.lastScannedCode)
    }
}
