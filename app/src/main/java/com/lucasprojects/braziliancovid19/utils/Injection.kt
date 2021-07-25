package com.lucasprojects.braziliancovid19.utils

import android.app.Application
import com.lucasprojects.braziliancovid19.ui.viewmodel.MainViewModelFactory

object Injection {
    fun providerViewModelFactory(application: Application) = MainViewModelFactory(application)
}