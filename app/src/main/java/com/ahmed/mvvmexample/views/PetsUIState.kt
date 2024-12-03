package com.ahmed.mvvmexample.views

import com.ahmed.mvvmexample.data.Cat

data class PetsUIState(
    val isLoading: Boolean = false,
    val pets: List<Cat> = emptyList(),
    val error: String? = null
)
