package com.cyberfeedforward.mycardmanager.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        uiState = uiState,
        onIncrementClicked = viewModel::onIncrementClicked,
        onDecrementClicked = viewModel::onDecrementClicked,
    )
}
