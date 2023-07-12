package com.example.nicehashtest.screen.accumulator.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.nicehashtest.screen.accumulator.AccumulatorScreen

const val accumulatorRuteNavigation = "accumulator_rute"

fun NavController.navigationAccumulator(
    navOptions: NavOptions? = null,
) {
    navigate(accumulatorRuteNavigation, navOptions)
}

fun NavGraphBuilder.accumulatorScreen(navigateToBack: () -> Unit) {
    composable(accumulatorRuteNavigation) {
        AccumulatorScreen(hiltViewModel(), navigateToBack = navigateToBack)
    }
}
