package com.cyberfeedforward.mycardmanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cyberfeedforward.mycardmanager.ui.screens.cards.CardsRoute
import com.cyberfeedforward.mycardmanager.ui.screens.home.HomeRoute
import com.cyberfeedforward.mycardmanager.ui.screens.settings.SettingsRoute

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestinationRoute: String = TopLevelDestination.Home.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestinationRoute,
        modifier = modifier,
    ) {
        composable(TopLevelDestination.Home.route) {
            HomeRoute()
        }
        composable(TopLevelDestination.Cards.route) {
            CardsRoute()
        }
        composable(TopLevelDestination.Settings.route) {
            SettingsRoute()
        }
    }
}
