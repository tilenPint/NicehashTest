package com.example.nicehashtest.screen.passport.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.nicehashtest.screen.passport.PassportScreen

const val passportRuteNavigation = "passport_rute"

fun NavController.navigationPassport(
    navOptions: NavOptions? = null,
) {
    navigate(passportRuteNavigation, navOptions)
}

fun NavGraphBuilder.passportScreen() {
    composable(passportRuteNavigation) {
        PassportScreen(hiltViewModel())
    }
}
