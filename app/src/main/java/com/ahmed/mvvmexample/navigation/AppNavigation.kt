package com.ahmed.mvvmexample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahmed.mvvmexample.data.Cat
import com.ahmed.mvvmexample.views.FavoritePetsScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun AppNavigation(
    contentType: ContentType,
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.PetsScreen.route
    ) {
        composable(Screens.PetsScreen.route) {
            PetsScreen(
                onPetClicked = { cat ->
                    navHostController.navigate(
                        "${Screens.PetsDetailsScreen.route}/${Json.encodeToString(cat)}"
                    )
                },
                contentType = contentType
            )
        }
        composable(
            route = "${Screens.PetsDetailsScreen.route}/{cat}",
            arguments = listOf(
                navArgument("cat") {
                    type = NavType.StringType
                }
            )
        ) {
            PetDetailsScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                },
                cat = Json.decodeFromString(it.arguments?.getString("cat") ?: "")
            )
        }
        composable(Screens.FavoritePetsScreen.route) {
            FavoritePetsScreen()
        }
    }
}