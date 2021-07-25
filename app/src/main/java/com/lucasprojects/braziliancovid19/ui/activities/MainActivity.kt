package com.lucasprojects.braziliancovid19.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.lucasprojects.braziliancovid19.R
import com.lucasprojects.braziliancovid19.databinding.ActivityMainBinding
import com.lucasprojects.braziliancovid19.utils.Injection

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mMainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initializeMainActivityViewModel()
        setupBottomNavigation()
    }

    private fun initializeMainActivityViewModel() {
        mMainActivityViewModel = ViewModelProvider(this, Injection.providerViewModelFactory(application)).get(MainActivityViewModel::class.java)
    }

    private fun setupBottomNavigation() {
        val bottomNavigation = mBinding.bottomNavigation
        val navController = findNavController(R.id.navHostFragment)
        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }
}