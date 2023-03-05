package com.example.contestapiintegration.data.repository


import com.example.contestapiintegration.data.api.WallpaperApiService
import com.example.contestapiintegration.data.model.WallpaperArticle
import retrofit2.Response
import javax.inject.Inject

class WallpaperRepository @Inject constructor(private val wallpaperApiService: WallpaperApiService) {

    suspend fun getWallpapers(): Response<WallpaperArticle> {
        return wallpaperApiService.getWallpapers()
    }
}