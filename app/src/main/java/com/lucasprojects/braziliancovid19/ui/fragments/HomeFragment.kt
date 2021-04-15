package com.lucasprojects.braziliancovid19.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.lucasprojects.braziliancovid19.databinding.FragmentHomeBinding
import com.lucasprojects.braziliancovid19.ui.activities.MainActivityViewModel
import com.lucasprojects.braziliancovid19.utils.Utils

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mViewRoot: View
    private val mMainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        mViewRoot = binding.root
        setupObservers()
        return mViewRoot
    }

    private fun setupObservers() {
        mMainActivityViewModel.mListData.observe(viewLifecycleOwner, { states ->
            _binding?.textDataConfirmed?.text = states?.sumBy { it.confirmeds!!.toInt() }.toString()
            _binding?.textDataDeaths?.text = states?.sumBy { it.deaths!!.toInt() }.toString()
            _binding?.textRefreshDate?.text = "Última Atualização : ${Utils.getCurrentDateHour()}"
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}