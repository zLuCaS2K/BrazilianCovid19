package com.lucasprojects.braziliancovid19.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lucasprojects.braziliancovid19.data.db.BrazilianAppDatabase
import com.lucasprojects.braziliancovid19.data.network.RetrofitRepository
import com.lucasprojects.braziliancovid19.data.network.RetrofitServiceClient
import com.lucasprojects.braziliancovid19.data.network.models.ResponseData
import com.lucasprojects.braziliancovid19.model.domain.data.Data
import com.lucasprojects.braziliancovid19.model.repository.DataRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _dataRepository: DataRepository
    private val _retrofitRepository: RetrofitRepository

    var mListData: LiveData<List<Data>>
    var mListDataCity = MutableLiveData<List<Data>>()
    var isLoading = MutableLiveData<Boolean>()
    var errorOccured = MutableLiveData<Boolean>()

    init {
        val db = BrazilianAppDatabase.getInstance(application)
        _dataRepository = DataRepository.create(db.getDataDAO())
        _retrofitRepository = RetrofitRepository.create(RetrofitServiceClient.getInstance())
        mListData = _dataRepository.getAllData()
        getAllData()
    }

    fun insertAllDataIntoDB(listData: List<Data>) {
        viewModelScope.launch {
            _dataRepository.insertAllData(listData)
            mListData = _dataRepository.getAllData()
        }
    }

    fun getAllData() {
        isLoading.postValue(true)
        _retrofitRepository.getAllData().enqueue(object : Callback<ResponseData> {
            override fun onResponse(
                call: Call<ResponseData>,
                responseData: Response<ResponseData>,
            ) {
                if (responseData.isSuccessful) {
                    responseData.body()?.let {
                        isLoading.postValue(false)
                        errorOccured.postValue(false)
                        insertAllDataIntoDB(it.data)
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