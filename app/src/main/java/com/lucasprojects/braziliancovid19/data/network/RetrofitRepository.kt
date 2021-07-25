package com.lucasprojects.braziliancovid19.data.network

class RetrofitRepository constructor(private val _retrofitServiceClient: RetrofitServiceClient) {

    fun getAllData() = _retrofitServiceClient.getAllData()

    fun getAllCity() = _retrofitServiceClient.getAllCity()

    companion object {
        fun create(retrofitServiceClient: RetrofitServiceClient): RetrofitRepository {
            return RetrofitRepository(retrofitServiceClient)
        }
    }
}