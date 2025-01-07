package com.ahmed.mvvmexample.navigation

sealed interface ContentType {
    object List : ContentType
    object ListAndDetail : ContentType
}