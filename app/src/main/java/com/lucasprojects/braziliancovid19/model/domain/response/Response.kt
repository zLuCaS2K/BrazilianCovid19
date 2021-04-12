package com.lucasprojects.braziliancovid19.model.domain.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lucasprojects.braziliancovid19.model.domain.data.Data

class Response {
    @SerializedName("results")
    @Expose
    var data: List<Data>? = null
}