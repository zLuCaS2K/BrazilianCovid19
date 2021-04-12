package com.lucasprojects.braziliancovid19.model.services

interface OperationCallback<T> {
    fun <T> onSuccess(data: List<T>)
    fun onError(error: String)
}