package com.ahmed.mvvmexample.navigation

sealed class Screens(val route: String) {
    object PetsScreen: Screens("pets")
    object PetsDetailsScreen: Screens("petDetails")
    object FavoritePetsScreen: Screens("favoritePets")
}



