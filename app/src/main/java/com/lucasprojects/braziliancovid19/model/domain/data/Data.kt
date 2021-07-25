package com.lucasprojects.braziliancovid19.model.domain.data

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("state")
    val uf: String,

    @SerializedName("city")
    val name: String,

    @SerializedName("confirmed")
    val confirmeds: Int,

    @SerializedName("deaths")
    val deaths: Int,

    @SerializedName("death_rate")
    val deathRate: String,

    @SerializedName("estimated_population_2019")
    val population: Int,

    @SerializedName("date")
    val date: String,
)