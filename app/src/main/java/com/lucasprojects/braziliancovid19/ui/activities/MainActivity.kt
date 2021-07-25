package com.lucasprojects.braziliancovid19.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.lucasprojects.braziliancovid19.R
import com.lucasprojects.braziliancovid19.databinding.ActivityMainBinding
import com.lucasprojects.braziliancovid19.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val mMainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setupBottomNavigation()
        setObservesUI()
    }

    private fun setObservesUI() {
        mMainViewModel.isLoading.observe(this, {
            if (it) {
                Log.v("TESTE", "Carregamento Iniciado!")
            } else {
                Log.v("TESTE", "Carregamento Iniciado!")
            }
        })
        mMainViewModel.errorOccured.observe(this, {
            if (it) {
                Log.v("TESTE", "Um Erro Aconteceu!")
            } else {
                Log.v("TESTE", "Sem Erros !")
            }
        })
    }

    private fun setupBottomNavigation() {
        val bottomNavigation = mBinding.bottomNavigation
        val navController = findNavController(R.id.navHostFragment)
        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }
}