package com.cyberfeedforward.mycardmanager.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyberfeedforward.mycardmanager.ui.theme.MyCardManagerTheme

@Composable
fun SettingsScreen(
    uiState: SettingsUiState,
    onNotificationsToggled: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineSmall,
        )

        Text(text = "Notifications")
        Switch(
            checked = uiState.notificationsEnabled,
            onCheckedChange = onNotificationsToggled,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    MyCardManagerTheme {
        SettingsScreen(
            uiState = SettingsUiState(notificationsEnabled = true),
            onNotificationsToggled = {},
        )
    }
}
