package com.cyberfeedforward.mycardmanager.ui.screens.cards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CardsRoute(
    viewModel: CardsViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    CardsScreen(
        uiState = uiState,
        onAddCardClicked = viewModel::onAddCardClicked,
        onRemoveCardClicked = viewModel::onRemoveCardClicked,
    )
}
