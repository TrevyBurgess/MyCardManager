package com.cyberfeedforward.mycardmanager

import com.cyberfeedforward.mycardmanager.ui.screens.settings.SettingsViewModel
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SettingsViewModelTest {
    @Test
    fun notifications_defaultDisabled() {
        val viewModel = SettingsViewModel()

        assertFalse(viewModel.uiState.value.notificationsEnabled)
    }

    @Test
    fun toggleNotifications_updatesState() {
        val viewModel = SettingsViewModel()

        viewModel.onNotificationsToggled(true)
        assertTrue(viewModel.uiState.value.notificationsEnabled)

        viewModel.onNotificationsToggled(false)
        assertFalse(viewModel.uiState.value.notificationsEnabled)
    }
}
