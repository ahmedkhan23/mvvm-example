package com.ahmed.mvvmexample.viewmodel

import androidx.lifecycle.ViewModel
import com.ahmed.mvvmexample.data.PetsRepository
import com.ahmed.mvvmexample.data.PetsRepositoryImpl

class PetsViewModel(private val petsRepository: PetsRepository) : ViewModel() {
    fun getPets() = petsRepository.getPets()
}