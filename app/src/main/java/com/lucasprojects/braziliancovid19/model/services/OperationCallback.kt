package com.lucasprojects.braziliancovid19.model.services

import com.lucasprojects.braziliancovid19.model.domain.response.Response

interface OperationCallback<T> {
    fun onSuccess(data: Response?)
    fun onError(error: String)
}