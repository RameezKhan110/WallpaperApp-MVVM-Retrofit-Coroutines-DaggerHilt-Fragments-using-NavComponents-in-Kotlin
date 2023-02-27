package com.example.contestapiintegration.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contestapiintegration.api.WallpaperApiService
import com.example.contestapiintegration.model.WallpaperArticle
import com.example.contestapiintegration.repository.WallpaperRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallpaperViewModel @Inject constructor(
    application: Application,
    private val wallpaperRepository: WallpaperRepository
) : AndroidViewModel(application) {

    val wallpaperList: MutableLiveData<WallpaperArticle> = MutableLiveData()
    val liveWallpaperList: LiveData<WallpaperArticle> = wallpaperList

    fun getWallpapers() {
        viewModelScope.launch() {
            val response = wallpaperRepository.getWallpapers()
            try {
                wallpaperList.postValue(response.body())
            } catch (t: Throwable) {
                Log.d("TAG", "Error in fetching data", t)
            }
        }
    }
}