package com.example.contestapiintegration.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class SharedViewModel : ViewModel() {

    private val mutablePageUrl = MutableLiveData<String>()
    val livePageUrl: LiveData<String> = mutablePageUrl

    fun getPageUrl(pageUrl: String) {
        mutablePageUrl.value = pageUrl
    }
}