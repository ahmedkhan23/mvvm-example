package com.ahmed.mvvmexample.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.mvvmexample.data.NetworkResult
import com.ahmed.mvvmexample.data.PetsRepository
import com.ahmed.mvvmexample.data.PetsRepositoryImpl
import com.ahmed.mvvmexample.views.PetsUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PetsViewModel(private val petsRepository: PetsRepository) : ViewModel() {


    val petsUIState = MutableStateFlow(PetsUIState())

    init {
        getPets()
    }


    private fun getPets() {
        petsUIState.value = PetsUIState(isLoading = true)
        viewModelScope.launch {
            when (val result = petsRepository.getPets()) {
                is NetworkResult.Success -> {
                    petsUIState.update {
                        it.copy(isLoading = false, pets = result.data)
                    }
                }

                is NetworkResult.Error -> {
                    petsUIState.update {
                        it.copy(isLoading = false, error = result.error)
                    }

                }
            }
        }
    }
}