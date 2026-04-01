package com.cyberfeedforward.mycardmanager.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.cyberfeedforward.mycardmanager.R

enum class TopLevelDestination(
    val route: String,
    @StringRes val labelResId: Int,
    val icon: ImageVector,
) {
    Home(
        route = "home",
        labelResId = R.string.nav_home,
        icon = Icons.Filled.Home,
    ),
    Cards(
        route = "cards",
        labelResId = R.string.nav_cards,
        icon = Icons.Filled.CreditCard,
    ),
    Settings(
        route = "settings",
        labelResId = R.string.nav_settings,
        icon = Icons.Filled.Settings,
    ),
}
