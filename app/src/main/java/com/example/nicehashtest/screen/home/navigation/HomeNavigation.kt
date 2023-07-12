package com.example.nicehashtest.screen.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.nicehashtest.screen.home.HomeScreen

const val homeRuteNavigation = "home_rute"

fun NavController.navigationHome(
    navOptions: NavOptions? = null,
) {
    navigate(homeRuteNavigation, navOptions)
}

fun NavGraphBuilder.homeScreen(navigateToAcc: () -> Unit, navigateToPassport: () -> Unit) {
    composable(homeRuteNavigation) {
        HomeScreen(navigateToAcc = navigateToAcc, navigateToPassport = navigateToPassport)
    }
}
