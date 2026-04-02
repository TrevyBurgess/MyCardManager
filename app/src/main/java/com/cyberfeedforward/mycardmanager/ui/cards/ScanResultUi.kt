package com.cyberfeedforward.mycardmanager.ui.cards

sealed interface ScanResultUi {
    data class Success(val value: String) : ScanResultUi
    data class Error(val message: String) : ScanResultUi
}
