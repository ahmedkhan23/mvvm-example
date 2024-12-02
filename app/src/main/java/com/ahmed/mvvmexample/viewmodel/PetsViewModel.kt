package com.ahmed.mvvmexample.viewmodel

import androidx.lifecycle.ViewModel
import com.ahmed.mvvmexample.data.PetsRepository
import com.ahmed.mvvmexample.data.PetsRepositoryImpl

class PetsViewModel: ViewModel() {
    private val petsRepository: PetsRepository = PetsRepositoryImpl()

    fun getPets() = petsRepository.getPets()
}