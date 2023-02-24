package com.example.contestapiintegration.api

import com.example.contestapiintegration.model.Hit
import com.example.contestapiintegration.model.WallpaperArticle
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//https://pixabay.com/api/?key=33819382-b357d1c19cd9fbb9db3c3226a
const val API_KEY = "33819382-b357d1c19cd9fbb9db3c3226a"
const val BASE_URL = "https://pixabay.com/api/"
interface WallpaperApiService {

    @GET("?key=$API_KEY")
    suspend fun getWallpapers() : Response<WallpaperArticle>
}

object WallpaperApi{

    val wallpaperApiService : WallpaperApiService

    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        wallpaperApiService = retrofit.create(WallpaperApiService::class.java)
    }


}