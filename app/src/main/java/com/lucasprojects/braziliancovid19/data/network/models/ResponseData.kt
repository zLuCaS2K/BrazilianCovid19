package com.lucasprojects.braziliancovid19.data.network.models

import com.google.gson.annotations.SerializedName
import com.lucasprojects.braziliancovid19.model.domain.data.Data

data class ResponseData(
    @SerializedName("results")
    val data: List<Data>,
)