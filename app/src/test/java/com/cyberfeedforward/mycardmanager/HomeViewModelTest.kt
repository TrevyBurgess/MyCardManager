package com.cyberfeedforward.mycardmanager

import com.cyberfeedforward.mycardmanager.ui.home.HomeViewModel
import org.junit.Assert.assertTrue
import org.junit.Test

class HomeViewModelTest {

    @Test
    fun initialState_hasNonBlankWelcomeMessage() {
        val viewModel = HomeViewModel()
        val message = viewModel.uiState.value.welcomeMessage

        assertTrue(message.isNotBlank())
        assertTrue(message.contains("MyCardManager"))
    }
}
