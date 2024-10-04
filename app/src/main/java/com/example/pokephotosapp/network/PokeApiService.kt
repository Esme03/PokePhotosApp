package com.example.pokephotosapp.network

import com.example.pokephotosapp.model.PokePhoto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET




private const val BASE_URL = "https://pokeapi.co/api/v2/pokemon/"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()


interface PokeApiService{
    @GET("pokemon/?limit=10")
    suspend fun getPhotos():List<PokePhoto>
}


object PokeApi {
    val retrofitService: PokeApiService by lazy{
        retrofit.create(PokeApiService::class.java)
    }
}
