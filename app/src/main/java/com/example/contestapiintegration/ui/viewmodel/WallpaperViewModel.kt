package com.example.contestapiintegration.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contestapiintegration.util.Resource
import com.example.contestapiintegration.data.model.WallpaperArticle
import com.example.contestapiintegration.data.repository.WallpaperRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallpaperViewModel @Inject constructor(
    private val wallpaperRepository: WallpaperRepository
) : ViewModel() {

    private val wallpaperList: MutableLiveData<Resource<WallpaperArticle>> = MutableLiveData()
    val liveWallpaperList: LiveData<Resource<WallpaperArticle>>
        get() = wallpaperList

    init {
        getWallpapers()
    }

    fun getWallpapers() {
        viewModelScope.launch() {
            wallpaperList.postValue(Resource.loading(null))
            val response = wallpaperRepository.getWallpapers()
            try {
                wallpaperList.postValue(Resource.success(response.body()))
            } catch (t: Throwable) {
                wallpaperList.postValue(Resource.error(response.errorBody().toString(), null))
            }

        }
    }
}