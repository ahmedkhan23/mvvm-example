package com.ahmed.mvvmexample.di

import com.ahmed.mvvmexample.data.CatsAPI
import com.ahmed.mvvmexample.data.PetsRepository
import com.ahmed.mvvmexample.data.PetsRepositoryImpl
import com.ahmed.mvvmexample.viewmodel.PetsViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Retrofit

val appModules = module {
    single<PetsRepository> { PetsRepositoryImpl(get(), get()) }

    single { Dispatchers.IO }

    single { PetsViewModel(get())}

    // retrofit instance
    single {
        Retrofit.Builder()
            .addConverterFactory(
                Json.asConverterFactory(contentType = "application/json".toMediaType())
            )
            .baseUrl("https://cataas.com/api")
            .build()
    }

    // Cats API instance
    single {
        get<Retrofit>().create(CatsAPI::class.java)
    }
}

