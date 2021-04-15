package com.lucasprojects.braziliancovid19.model.repository

import android.app.Application
import com.lucasprojects.braziliancovid19.model.domain.response.Response
import com.lucasprojects.braziliancovid19.model.domain.response.ResponseDataSource
import com.lucasprojects.braziliancovid19.model.services.APIClient
import com.lucasprojects.braziliancovid19.model.services.OperationCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import kotlin.coroutines.CoroutineContext

class ResponseRepository(application: Application) : CoroutineScope, ResponseDataSource {

    private var _callResponse: Call<Response>? = null
    override val coroutineContext: CoroutineContext get() = Dispatchers.IO

    override fun retrieveResponse(callback: OperationCallback<Response>) {
        _callResponse = APIClient.build().getAllData()
        _callResponse?.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                t.message?.let { callback.onError(it) }
            }
        })
    }

    override fun retrieveResponseCity(callback: OperationCallback<Response>) {
        _callResponse = APIClient.build().getAllCity(5000)
        _callResponse?.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                t.message?.let { callback.onError(it) }
            }
        })
    }

    override fun cancel() {
        _callResponse?.cancel()
    }

}