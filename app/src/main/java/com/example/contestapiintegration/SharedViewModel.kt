package com.example.contestapiintegration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    val MutableUrl = MutableLiveData<String>()

    val url : LiveData<String> = MutableUrl

    fun getUrl(url : String){
        MutableUrl.value = url
    }
}