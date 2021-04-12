package com.lucasprojects.braziliancovid19.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.lucasprojects.braziliancovid19.R
import github.com.st235.lib_expandablebottombar.ExpandableBottomBar
import github.com.st235.lib_expandablebottombar.navigation.ExpandableBottomBarNavigationUI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: MainActivityViewModel by viewModels()
        model.mListData.observe(this, { data ->
            Log.v("APICOVID", "MainActivity: $data")
        })

        val bottomNavigationMain = findViewById<ExpandableBottomBar>(R.id.bottomNavigationMain)
        val navController = findNavController(R.id.navHostFragment)
        ExpandableBottomBarNavigationUI.setupWithNavController(bottomNavigationMain, navController)
    }
}