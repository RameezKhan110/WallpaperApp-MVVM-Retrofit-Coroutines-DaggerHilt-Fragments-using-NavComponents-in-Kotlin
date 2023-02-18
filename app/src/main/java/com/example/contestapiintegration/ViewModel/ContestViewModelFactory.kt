package com.example.contestapiintegration.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contestapiintegration.Repository.ContestRepository

class ContestViewModelFactory(val contestRepository: ContestRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContestViewModel(contestRepository) as T
    }
}