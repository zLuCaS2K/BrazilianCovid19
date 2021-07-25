package com.lucasprojects.braziliancovid19.model.domain.response

import com.google.gson.annotations.SerializedName
import com.lucasprojects.braziliancovid19.model.domain.data.Data

data class Response(
    @SerializedName("results")
    val data: List<Data>,
)