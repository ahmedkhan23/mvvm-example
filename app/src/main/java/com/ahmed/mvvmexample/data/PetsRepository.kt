package com.ahmed.mvvmexample.data

interface PetsRepository {
    suspend fun getPets(): NetworkResult<List<Cat>>
}