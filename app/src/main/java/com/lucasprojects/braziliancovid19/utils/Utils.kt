package com.lucasprojects.braziliancovid19.utils

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import com.lucasprojects.braziliancovid19.R
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun getCurrentDateHour(context: Context): String {
        val date = SimpleDateFormat("dd/MM/yyyy").format(Date())
        val hour = SimpleDateFormat("HH:mm:ss").format(Date())
        return "${context.resources.getString(R.string.last_verify)} $date $hour"
    }

    fun formatNumberData(number: Int) = DecimalFormat("#,###").format(number)

    fun formatDeathPercent(deathPercent: Double?): String =
        DecimalFormat("#,##0.00").format(deathPercent)

    fun setAnimation(context: Context, view: View, rawAnimation: Int) {
        val animation = AnimationUtils.loadAnimation(context, rawAnimation)
        view.startAnimation(animation)
    }

    fun setNameState(singleState: String?): String? {
        var nameState: String? = null
        when (singleState) {
            "AC" -> nameState = "Acré"
            "AL" -> nameState = "Alagoas"
            "AP" -> nameState = "Amapá"
            "AM" -> nameState = "Amazonas"
            "BA" -> nameState = "Bahia"
            "CE" -> nameState = "Ceará"
            "DF" -> nameState = "Distrito Federal"
            "ES" -> nameState = "Espírito Santo"
            "GO" -> nameState = "Goiás"
            "MA" -> nameState = "Maranhão"
            "MT" -> nameState = "Mato Grosso"
            "MS" -> nameState = "Mato Grosso do Sul"
            "MG" -> nameState = "Minas Gerais"
            "PA" -> nameState = "Pará"
            "PB" -> nameState = "Paraíba"
            "PR" -> nameState = "Paraná"
            "PE" -> nameState = "Pernambuco"
            "PI" -> nameState = "Piauí"
            "RJ" -> nameState = "Rio de Janeiro"
            "RN" -> nameState = "Rio Grande do Norte"
            "RS" -> nameState = "Rio Grande do Sul"
            "RO" -> nameState = "Rondônia"
            "RR" -> nameState = "Roraima"
            "SC" -> nameState = "Santa Catarina"
            "SP" -> nameState = "São Paulo"
            "SE" -> nameState = "Sergipe"
            "TO" -> nameState = "Tocantins"
            else -> {
                nameState = singleState
            }
        }
        return nameState
    }
}