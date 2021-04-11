package com.lucasprojects.braziliancovid19.ui.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lucasprojects.braziliancovid19.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(this, "Chamando próxima tela!", Toast.LENGTH_SHORT).show()
        }, 1500)
    }
}