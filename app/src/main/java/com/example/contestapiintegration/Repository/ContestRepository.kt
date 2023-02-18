package com.example.contestapiintegration.Repository

import com.example.contestapiintegration.Model.ContestDataItem
import com.example.contestapiintegration.ContestService
import retrofit2.Response

class ContestRepository() {

    suspend fun getAllContest(): Response<List<ContestDataItem>> {
        return ContestService.apiInterface.getContest()
    }
}