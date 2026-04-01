package com.cyberfeedforward.mycardmanager.ui.screens.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class HomeUiState(
    val title: String = "MyCardManager",
    val counter: Int = 0,
)

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun onIncrementClicked() {
        _uiState.update { it.copy(counter = it.counter + 1) }
    }

    fun onDecrementClicked() {
        _uiState.update { current ->
            current.copy(counter = (current.counter - 1).coerceAtLeast(0))
        }
    }
}
