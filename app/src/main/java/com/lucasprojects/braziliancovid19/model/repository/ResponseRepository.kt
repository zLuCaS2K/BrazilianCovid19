package com.lucasprojects.braziliancovid19.model.repository

import android.app.Application
import android.util.Log
import com.lucasprojects.braziliancovid19.model.domain.response.Response
import com.lucasprojects.braziliancovid19.model.domain.response.ResponseDataSource
import com.lucasprojects.braziliancovid19.model.services.APIClient
import com.lucasprojects.braziliancovid19.model.services.OperationCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import kotlin.coroutines.CoroutineContext

class ResponseRepository(app: Application) : CoroutineScope, ResponseDataSource {

    override val coroutineContext: CoroutineContext get() = Dispatchers.IO
    private var _callResponse: Call<List<Response>?>? = null

    override fun retrieveResponse(callback: OperationCallback<Response>) {
        _callResponse = APIClient.build().getAllData()
        _callResponse?.enqueue(object : Callback<List<Response>?> {
            override fun onResponse(call: Call<List<Response>?>, response: retrofit2.Response<List<Response>?>, ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(it)
                        Log.v("APICOVID", "$it")
                    }
                }
            }

            override fun onFailure(call: Call<List<Response>?>, t: Throwable) {
                t.message?.let { callback.onError(it) }
            }
        })
    }

    override fun cancel() {
        _callResponse?.cancel()
    }

}