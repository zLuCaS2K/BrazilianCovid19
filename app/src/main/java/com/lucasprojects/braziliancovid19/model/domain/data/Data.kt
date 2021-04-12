package com.lucasprojects.braziliancovid19.model.domain.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {

    @SerializedName("state")
    @Expose
    var uf: String? = null

    @SerializedName("city")
    @Expose
    var name: String? = null

    @SerializedName("confirmed")
    @Expose
    var confirmeds: String? = null

    @SerializedName("deaths")
    @Expose
    var deaths: String? = null

    @SerializedName("death_rate")
    @Expose
    var deathRate: String? = null

    @SerializedName("estimated_population_2019")
    @Expose
    var population: Int? = null

    @SerializedName("date")
    @Expose
    var date: String? = null
}