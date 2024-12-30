package com.ahmed.mvvmexample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahmed.mvvmexample.data.Cat
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.PetsScreen.route
    ) {
        composable(Screens.PetsScreen.route) {
            PetsScreen(onPetClicked = { cat ->
                navController.navigate(
                    "${Screens.PetsDetailsScreen.route}/${Json.encodeToString(cat)}"
                )
            })
        }

        composable("${Screens.PetsDetailsScreen.route}/{cat}",
                arguments = listOf(navArgument("cat") {
                    type = NavType.StringType
                }
            )
        )
        {
            PetDetailsScreen(
                onBackPressed = {
                    navController.popBackStack()
                },
                cat = Json.decodeFromString(it.arguments?.getString("cat") ?: "")
            )
        }
    }
}