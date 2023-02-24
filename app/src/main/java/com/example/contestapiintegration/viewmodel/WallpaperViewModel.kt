package com.example.contestapiintegration.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contestapiintegration.model.WallpaperArticle
import com.example.contestapiintegration.repository.WallpaperRepository
import kotlinx.coroutines.launch

class WallpaperViewModel(application: Application) : AndroidViewModel(application) {

    val wallpaperList: MutableLiveData<WallpaperArticle> = MutableLiveData()
    val liveWallpaperList: LiveData<WallpaperArticle> = wallpaperList
    private var repository: WallpaperRepository = WallpaperRepository()

    fun getWallpapers() {
        viewModelScope.launch() {
            val response = repository.getWallpapers()
            try {
                wallpaperList.postValue(response.body())
            } catch (t: Throwable) {
                Log.d("TAG", "Error in fetching data", t)
            }
        }
    }
}