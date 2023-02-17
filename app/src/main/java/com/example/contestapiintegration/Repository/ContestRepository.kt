package com.example.contestapiintegration.Repository

import com.example.contestapiintegration.ContestDataItem
import com.example.contestapiintegration.ContestService
import retrofit2.Response
import java.util.concurrent.CompletionService

class ContestRepository() {

    suspend fun getAllContest(): Response<List<ContestDataItem>> {
        return ContestService.apiInterface.getContest()
    }
}