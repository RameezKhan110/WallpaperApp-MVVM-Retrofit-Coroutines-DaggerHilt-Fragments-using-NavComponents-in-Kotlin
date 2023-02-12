package com.example.contestapiintegration

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL  = "https://kontests.net/api/"

object ContestService {

    val apiInterface : ApiInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiInterface = retrofit.create(ApiInterface::class.java)
    }
}