package com.example.contestapiintegration.repository


import com.example.contestapiintegration.api.WallpaperApiService
import com.example.contestapiintegration.model.WallpaperArticle
import retrofit2.Response
import javax.inject.Inject

class WallpaperRepository @Inject constructor(private val wallpaperApiService: WallpaperApiService) {

    suspend fun getWallpapers(): Response<WallpaperArticle> {
        return wallpaperApiService.getWallpapers()
    }
}