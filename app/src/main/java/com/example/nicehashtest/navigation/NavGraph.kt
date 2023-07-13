package com.example.nicehashtest.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.nicehashtest.screen.accumulator.navigation.accumulatorScreen
import com.example.nicehashtest.screen.accumulator.navigation.navigationAccumulator
import com.example.nicehashtest.screen.home.navigation.homeRuteNavigation
import com.example.nicehashtest.screen.home.navigation.homeScreen
import com.example.nicehashtest.screen.passport.navigation.navigationPassport
import com.example.nicehashtest.screen.passport.navigation.passportScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = homeRuteNavigation,
            modifier = Modifier.padding(innerPadding),
        ) {
            homeScreen(
                navigateToAcc = { navController.navigationAccumulator() },
                navigateToPassport = { navController.navigationPassport() },
            )
            accumulatorScreen { navController.navigateUp() }
            passportScreen { navController.navigateUp() }
        }
    }
}
