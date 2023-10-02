package com.example.workmanagerretrofitdemo.network

import com.example.workmanagerretrofitdemo.data.DogResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("random")
    suspend fun getRandomDog(): Response<DogResponse>
}