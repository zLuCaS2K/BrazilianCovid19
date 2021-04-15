package com.lucasprojects.braziliancovid19.ui.activities

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasprojects.braziliancovid19.model.domain.data.Data
import com.lucasprojects.braziliancovid19.model.domain.response.Response
import com.lucasprojects.braziliancovid19.model.repository.ResponseRepository
import com.lucasprojects.braziliancovid19.model.services.OperationCallback
import com.lucasprojects.braziliancovid19.utils.Constants
import com.lucasprojects.braziliancovid19.utils.DataStoreApp
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : ViewModel() {

    private val _responseRepository = ResponseRepository(application)
    private val _dataStoreApp = DataStoreApp(application)

    private val _listResponse = MutableLiveData<List<Data>>()
    private val _isViewLoading = MutableLiveData<Boolean>()
    private val _anErrorOccurred = MutableLiveData<Boolean>()
    private val _counterConfirmed = MutableLiveData<String>()
    private val _counterDeath = MutableLiveData<String>()
    private val _counterDate = MutableLiveData<String>()

    val mListData: LiveData<List<Data>> get() = _listResponse
    val mIsViewLoading: LiveData<Boolean> get() = _isViewLoading
    val mAnErrorOccurred: LiveData<Boolean> get() = _isViewLoading
    val mCounterConfirmed: LiveData<String> get() = _counterConfirmed
    val mCounterDeath: LiveData<String> get() = _counterDeath
    val mCounterDate: LiveData<String> get() = _counterDate

    init {
        loadAllData()
    }

    fun loadAllData() {
        _isViewLoading.postValue(true)
        _responseRepository.retrieveResponse(object : OperationCallback<Response> {
            override fun onSuccess(data: Response?) {
                _isViewLoading.postValue(false)
                data?.let { _listResponse.value = it.data }
                viewModelScope.launch {
                    saveDataStoreSuspend(
                        _listResponse.value?.sumBy { it.confirmeds!! }.toString(),
                        _listResponse.value?.sumBy { it.deaths!! }.toString(),
                        _listResponse.value?.first()?.date.toString()
                    )
                }
            }

            override fun onError(error: String) {
                _isViewLoading.postValue(false)
                _anErrorOccurred.postValue(true)
            }
        })
    }

    fun loadAllDataCity() {
        _isViewLoading.postValue(true)
        _responseRepository.retrieveResponse(object : OperationCallback<Response> {
            override fun onSuccess(data: Response?) {
                _isViewLoading.postValue(false)
                data?.let { _listResponse.value = it.data }
            }

            override fun onError(error: String) {
                _isViewLoading.postValue(false)
                _anErrorOccurred.postValue(true)
            }
        })
    }

    fun loadDataStore() {
        viewModelScope.launch { loadDataStoreSuspend() }
    }

    private suspend fun saveDataStoreSuspend(valueConfirmed: String, valueDeath: String, valueDate: String) {
        _counterConfirmed.value = valueConfirmed
        _counterDeath.value = valueDeath
        _counterDate.value = valueDate
        _dataStoreApp.saveData(Constants.DATA_STORE_KEYS.KEY_CONFIRMED, valueConfirmed)
        _dataStoreApp.saveData(Constants.DATA_STORE_KEYS.KEY_DEATH, valueDeath)
        _dataStoreApp.saveData(Constants.DATA_STORE_KEYS.KEY_DATE, valueDate)
    }

    private suspend fun loadDataStoreSuspend() {
        _counterConfirmed.value = _dataStoreApp.readData(Constants.DATA_STORE_KEYS.KEY_CONFIRMED).first()
        _counterDeath.value = _dataStoreApp.readData(Constants.DATA_STORE_KEYS.KEY_DEATH).first()
        _counterDate.value = _dataStoreApp.readData(Constants.DATA_STORE_KEYS.KEY_DATE).first()
    }
}