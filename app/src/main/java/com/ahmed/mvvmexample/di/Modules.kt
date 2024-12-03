package com.ahmed.mvvmexample.di

import com.ahmed.mvvmexample.data.PetsRepository
import com.ahmed.mvvmexample.data.PetsRepositoryImpl
import com.ahmed.mvvmexample.viewmodel.PetsViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Retrofit

val appModules = module {
    single<PetsRepository> { PetsRepositoryImpl() }

    single { PetsViewModel(get())}

    single {
        Retrofit.Builder()
            .addConverterFactory(
                Json.asConverterFactory(contentType = "application/json".toMediaType())
            )
            .baseUrl("https://cataas.com/api")
            .build()
    }
}

