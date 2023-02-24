package com.example.contestapiintegration.repository

import com.example.contestapiintegration.api.WallpaperApi
import com.example.contestapiintegration.api.WallpaperApiService
import com.example.contestapiintegration.model.WallpaperArticle
import retrofit2.Response

class WallpaperRepository {


    suspend fun getWallpapers() : Response<WallpaperArticle> {
        return WallpaperApi.wallpaperApiService.getWallpapers()
    }
}