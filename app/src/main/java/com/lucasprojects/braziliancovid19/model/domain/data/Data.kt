package com.lucasprojects.braziliancovid19.model.domain.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Data(
    @SerializedName("state")
    val uf: String,

    @SerializedName("city")
    @ColumnInfo(defaultValue = "")
    val name: String,

    @PrimaryKey
    @SerializedName("city_ibge_code")
    val ibgeCode: String,

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