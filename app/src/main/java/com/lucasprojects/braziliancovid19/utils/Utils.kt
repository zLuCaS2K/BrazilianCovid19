package com.lucasprojects.braziliancovid19.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun getCurrentDateHour(): String {
        val date = SimpleDateFormat("dd/MM/yyyy").format(Date())
        val hour = SimpleDateFormat("HH:mm:ss").format(Date())
        return "$date $hour"
    }
}