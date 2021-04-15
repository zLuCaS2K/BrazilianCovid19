package com.lucasprojects.braziliancovid19.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.lucasprojects.braziliancovid19.R
import com.lucasprojects.braziliancovid19.utils.Injection
import github.com.st235.lib_expandablebottombar.ExpandableBottomBar
import github.com.st235.lib_expandablebottombar.navigation.ExpandableBottomBarNavigationUI

class MainActivity : AppCompatActivity() {

    private lateinit var mMainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeMainActivityViewModel()
        setupExpandableBottomBarNavigation()
    }

    private fun initializeMainActivityViewModel() {
        mMainActivityViewModel = ViewModelProvider(this, Injection.providerViewModelFactory(application)).get(MainActivityViewModel::class.java)
    }

    private fun setupExpandableBottomBarNavigation() {
        val bottomNavigationMain = findViewById<ExpandableBottomBar>(R.id.bottomNavigationMain)
        val navController = findNavController(R.id.navHostFragment)
        ExpandableBottomBarNavigationUI.setupWithNavController(bottomNavigationMain, navController)
    }
}