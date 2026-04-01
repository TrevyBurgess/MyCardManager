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
}
