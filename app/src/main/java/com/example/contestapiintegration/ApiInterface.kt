package com.example.contestapiintegration

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("v1/all")
    suspend fun getContest() : Response<List<ContestDataItem>>
}