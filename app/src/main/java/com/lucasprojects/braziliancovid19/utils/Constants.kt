package com.lucasprojects.braziliancovid19.utils

object Constants {
    object APICOVID {
        const val BASE_URL = "https://brasil.io/api/dataset/covid19/caso/"
        const val GET_ALL = "data?is_last=True&place_type=state"
        const val GET_CITY = "data/?is_last=True&place_type=city"
    }
}