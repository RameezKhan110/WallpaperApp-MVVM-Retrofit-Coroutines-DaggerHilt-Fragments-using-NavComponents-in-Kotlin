package com.example.contestapiintegration.data.api

import com.example.contestapiintegration.util.Constants
import com.example.contestapiintegration.data.model.WallpaperArticle
import retrofit2.Response
import retrofit2.http.GET

//https://pixabay.com/api/?key=33819382-b357d1c19cd9fbb9db3c3226a

interface WallpaperApiService {

    @GET("?key=${Constants.API_KEY}")
    suspend fun getWallpapers(): Response<WallpaperArticle>
}