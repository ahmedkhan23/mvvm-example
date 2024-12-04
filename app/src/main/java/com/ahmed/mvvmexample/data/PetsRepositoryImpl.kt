package com.ahmed.mvvmexample.data

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PetsRepositoryImpl(
    private val catsAPI: CatsAPI,
    private val dispatcher: CoroutineDispatcher
): PetsRepository {

    val TAG = PetsRepository::class.java.simpleName

    override suspend fun getPets(): NetworkResult<List<Cat>> {

        return withContext(dispatcher) {
            try {
                val response = catsAPI.fetchCats("cute")
                Log.d(TAG, "")
                if (response.isSuccessful) {
                    NetworkResult.Success(response.body()!!)
                } else {
                    NetworkResult.Error(response.errorBody().toString())
                }
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error")
            }
        }

    }
}