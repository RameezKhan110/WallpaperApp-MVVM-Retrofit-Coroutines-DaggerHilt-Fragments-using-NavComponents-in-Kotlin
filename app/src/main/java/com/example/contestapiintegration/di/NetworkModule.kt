package com.example.contestapiintegration.di

import com.example.contestapiintegration.api.WallpaperApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesBaseUrl(): String = "https://pixabay.com/api/"

    @Provides
    @Singleton
    fun providesRetrofitBuilder(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun providesWallpaperApiService(retrofit: Retrofit): WallpaperApiService =
        retrofit.create(WallpaperApiService::class.java)
}