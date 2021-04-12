package com.lucasprojects.braziliancovid19.model.domain.response

import com.lucasprojects.braziliancovid19.model.services.OperationCallback

interface ResponseDataSource {
    fun retrieveResponse(callback: OperationCallback<Response>)
    fun cancel()
}