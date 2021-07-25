package com.lucasprojects.braziliancovid19.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucasprojects.braziliancovid19.data.network.RetrofitRepository
import com.lucasprojects.braziliancovid19.data.network.RetrofitServiceClient
import com.lucasprojects.braziliancovid19.model.domain.data.Data
import com.lucasprojects.braziliancovid19.data.network.models.ResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    var mListData = MutableLiveData<List<Data>>()
    var mListDataCity = MutableLiveData<List<Data>>()
    var isLoading = MutableLiveData<Boolean>()
    var errorOccured = MutableLiveData<Boolean>()
    private val _retrofitRepository = RetrofitRepository.create(RetrofitServiceClient.getInstance())

    init {
        getAllData()
    }

    fun getAllData() {
        isLoading.postValue(true)
        _retrofitRepository.getAllData().enqueue(object : Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, responseData: Response<ResponseData>, ) {
                if (responseData.isSuccessful) {
                    responseData.body()?.let {
                        isLoading.postValue(false)
                        errorOccured.postValue(false)
                        mListData.postValue(it.data)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                isLoading.postValue(false)
                errorOccured.postValue(true)
                t.printStackTrace()
            }
        })
    }

    fun getAllCity() {
        isLoading.postValue(true)
        _retrofitRepository.getAllCity().enqueue(object : Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, responseData: Response<ResponseData>, ) {
                if (responseData.isSuccessful) {
                    responseData.body()?.let {
                        isLoading.postValue(false)
                        errorOccured.postValue(false)
                        mListDataCity.postValue(it.data)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                isLoading.postValue(false)
                errorOccured.postValue(true)
                t.printStackTrace()
            }
        })
    }
}