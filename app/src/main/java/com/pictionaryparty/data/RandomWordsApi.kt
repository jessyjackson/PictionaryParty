package com.pictionaryparty.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface RandomWordsApi {
    @GET("api/words?language=en")
    suspend fun getRandomWords(): List<String>

    companion object {
        private const val BASE_URL = "https://pictionaryparty.azurewebsites.net/"

        operator fun invoke(): RandomWordsApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(RandomWordsApi::class.java)
        }
    }
}
