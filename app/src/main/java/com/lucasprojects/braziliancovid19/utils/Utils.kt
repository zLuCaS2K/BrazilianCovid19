package com.lucasprojects.braziliancovid19.utils

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.lucasprojects.braziliancovid19.R
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun getCurrentDateHour(context: Context): String {
        val nowDate = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date())
        return "${context.resources.getString(R.string.last_verify)} $nowDate"
    }

    fun formatDate(date : String?): String {
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val dateReplace = date?.replace("-", "/") as String
        val dateString = SimpleDateFormat("yyyy/MM/dd").parse(dateReplace) as Date
        return simpleDateFormat.format(dateString)
    }

    fun formatNumberData(number: Int) : String {
        return DecimalFormat("#,###").format(number)
    }

    fun showAlertDialog(context: Context, requestDialog: Int): Dialog {
        val builder = AlertDialog.Builder(context)
        val inflaterView = if (requestDialog == 1) getLayoutDialogLoading(context) else getLayoutDialogAnError(context)
        builder.setView(inflaterView)
        builder.setCancelable(false)
        val alertDialog = builder.create()
        alertDialog.window?.let {
            it.setBackgroundDrawable(ColorDrawable(0))
            if (requestDialog == 2) it.attributes.windowAnimations = R.style.DialogAnimationError
        }
        return alertDialog
    }

    fun formatDeathPercent(deathPercent: Double?): String =
        DecimalFormat("#,##0.00").format(deathPercent)

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

    private fun getLayoutDialogLoading(context: Context) = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null)

    private fun getLayoutDialogAnError(context: Context) = LayoutInflater.from(context).inflate(R.layout.dialog_error, null)

}