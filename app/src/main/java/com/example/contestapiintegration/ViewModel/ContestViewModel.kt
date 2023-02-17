package com.example.contestapiintegration.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contestapiintegration.ContestDataItem
import com.example.contestapiintegration.Repository.ContestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ContestViewModel(private val contestRepository: ContestRepository) : ViewModel() {

    var contestList = MutableLiveData<List<ContestDataItem>>()
    fun getAllContest() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = contestRepository.getAllContest()
            if (result.isSuccessful) {
                contestList.postValue(result.body())
            } else {
                Log.d("TAG", "Error in Fetching data")
            }
        }
    }
}