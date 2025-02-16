package com.ahmed.mvvmexample.di

import android.util.Log
import com.ahmed.mvvmexample.data.CatsAPI
import com.ahmed.mvvmexample.data.PetsRepository
import com.ahmed.mvvmexample.data.PetsRepositoryImpl
import com.ahmed.mvvmexample.viewmodel.PetsViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

const val TAG = "Modules"

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

val appModules = module {
    single<PetsRepository> { PetsRepositoryImpl(get(), get()) }

    single { Dispatchers.IO }

    single { PetsViewModel(get())}

    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    // retrofit instance
    single {
        Retrofit.Builder()
            .addConverterFactory(
                json.asConverterFactory(contentType = "application/json".toMediaType())
            )
            .baseUrl("https://cataas.com/api/")
            .client(client)
            .build()
    }

    // Cats API instance
    single {
        get<Retrofit>().create(CatsAPI::class.java)
    }
}

