package com.lucasprojects.braziliancovid19.ui.activities

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucasprojects.braziliancovid19.model.domain.data.Data
import com.lucasprojects.braziliancovid19.model.domain.response.Response
import com.lucasprojects.braziliancovid19.model.repository.ResponseRepository
import com.lucasprojects.braziliancovid19.model.services.OperationCallback

class MainActivityViewModel(application: Application) : ViewModel() {

    private val _responseRepository = ResponseRepository(application)

    private val _listResponse = MutableLiveData<List<Data>>()
    val mListData: LiveData<List<Data>> = _listResponse

    private val _isViewLoading = MutableLiveData<Boolean>()
    val mIsViewLoading: LiveData<Boolean> = _isViewLoading

    private val _anErrorOccurred = MutableLiveData<Boolean>()
    val mAnErrorOccurred: LiveData<Boolean> = _isViewLoading

    init {
        loadAllData()
    }

    fun loadAllData() {
        _isViewLoading.postValue(true)
        _responseRepository.retrieveResponse(object : OperationCallback<Response> {
            override fun onSuccess(data: Response?) {
                _isViewLoading.postValue(false)
                data?.let {
                    _listResponse.value = it.data
                    Log.v("TESTE", "${it.data}")
                }
            }

            override fun onError(error: String) {
                _isViewLoading.postValue(false)
                _anErrorOccurred.postValue(true)
                Log.v("TESTE", "error")
            }
        })
    }

    fun loadAllDataCity() {
        _isViewLoading.postValue(true)
        _responseRepository.retrieveResponse(object : OperationCallback<Response> {
            override fun onSuccess(data: Response?) {
                _isViewLoading.postValue(false)
                data?.let {
                    _listResponse.value = it.data
                }
            }

            override fun onError(error: String) {
                _isViewLoading.postValue(false)
                _anErrorOccurred.postValue(true)
            }
        })
    }
}