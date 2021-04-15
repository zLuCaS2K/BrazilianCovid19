package com.lucasprojects.braziliancovid19.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.lucasprojects.braziliancovid19.databinding.FragmentHomeBinding
import com.lucasprojects.braziliancovid19.ui.activities.MainActivityViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mViewRoot: View
    private val mMainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        mViewRoot = binding.root
        setupObservers()
        setupDataDataStore()
        return mViewRoot
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding?.btnRefreshData?.setOnClickListener { mMainActivityViewModel.loadAllData() }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupObservers() {
        mMainActivityViewModel.mCounterConfirmed.observe(viewLifecycleOwner, {
            _binding?.textDataConfirmed?.text = it
        })
        mMainActivityViewModel.mCounterDeath.observe(viewLifecycleOwner, {
            _binding?.textDataDeaths?.text = it
        })
        mMainActivityViewModel.mCounterDate.observe(viewLifecycleOwner, {
            _binding?.textRefreshDate?.text = it
        })
    }

    private fun setupDataDataStore() {
        mMainActivityViewModel.loadDataStore()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}