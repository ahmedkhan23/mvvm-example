package com.ahmed.mvvmexample.data

interface PetsRepository {
    fun getPets(): List<Pet>
}