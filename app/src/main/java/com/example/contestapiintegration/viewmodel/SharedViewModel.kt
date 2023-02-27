package com.example.contestapiintegration.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    val mutablePageUrl = MutableLiveData<String>()
    val livePageUrl: LiveData<String> = mutablePageUrl

    fun getPageUrl(pageUrl: String) {
        mutablePageUrl.value = pageUrl
    }
}