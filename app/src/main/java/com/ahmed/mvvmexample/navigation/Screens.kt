package com.ahmed.mvvmexample.navigation

sealed class Screens(val route: String) {
    object PetsScreen: Screens("pets")
    object PetsDetailsScreen: Screens("petDetails")
}

/*
class Xyz(val test: String) {
    val test2: Xyz = Xyz("hello")

    fun test3() {

    }
}

 */