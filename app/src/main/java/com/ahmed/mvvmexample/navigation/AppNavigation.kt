package com.ahmed.mvvmexample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahmed.mvvmexample.data.Cat

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.PetsScreen.route
    ) {
        composable(Screens.PetsScreen.route) {
            PetsScreen(onPetClicked = { })
        }
    }
}