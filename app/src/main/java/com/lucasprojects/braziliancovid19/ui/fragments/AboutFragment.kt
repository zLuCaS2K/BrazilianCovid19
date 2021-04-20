package com.lucasprojects.braziliancovid19.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lucasprojects.braziliancovid19.databinding.FragmentAboutBinding
import com.lucasprojects.braziliancovid19.utils.Constants

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        eventsClick()
        return binding.root
    }

    private fun eventsClick() {
        binding.btnGoRepositoryGitHun.setOnClickListener { openUrlFromIntent(Constants.ABOUT.REPOSITORY_URL) }
        binding.cardUpdateApp.setOnClickListener { openUrlFromIntent(Constants.ABOUT.REPOSITORY_UPDATE_URL) }
        binding.cardShareApp.setOnClickListener { shareApp() }
        binding.cardLicenses.setOnClickListener {  }
    }

    private fun openUrlFromIntent(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun shareApp() {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "https://github.com/zLuCaS2K/BrazilianCovid19/releases \nAplicativo de monitoramento do Covid-19 no Brasil.")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(intent, "Compartilhar Aplicativo"))
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}