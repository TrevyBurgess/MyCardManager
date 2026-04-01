package com.cyberfeedforward.mycardmanager

import com.cyberfeedforward.mycardmanager.ui.screens.home.HomeViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeViewModelTest {
    @Test
    fun increment_increasesCounter() {
        val viewModel = HomeViewModel()

        viewModel.onIncrementClicked()

        assertEquals(1, viewModel.uiState.value.counter)
    }

    @Test
    fun decrement_atZero_staysAtZero() {
        val viewModel = HomeViewModel()

        viewModel.onDecrementClicked()

        assertEquals(0, viewModel.uiState.value.counter)
    }

    @Test
    fun decrement_afterIncrement_decreasesCounter() {
        val viewModel = HomeViewModel()

        viewModel.onIncrementClicked()
        viewModel.onIncrementClicked()
        viewModel.onDecrementClicked()

        assertEquals(1, viewModel.uiState.value.counter)
    }
}
