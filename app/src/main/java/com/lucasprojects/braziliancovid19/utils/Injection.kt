package com.lucasprojects.braziliancovid19.utils

import android.app.Application
import com.lucasprojects.braziliancovid19.ui.activities.MainActivityViewModelFactory

object Injection {
    fun providerViewModelFactory(application: Application) = MainActivityViewModelFactory(application)
}