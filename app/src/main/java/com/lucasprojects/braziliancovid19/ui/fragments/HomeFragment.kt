package com.lucasprojects.braziliancovid19.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucasprojects.braziliancovid19.R
import com.lucasprojects.braziliancovid19.databinding.FragmentHomeBinding
import com.lucasprojects.braziliancovid19.model.domain.symptoms.Symptoms
import com.lucasprojects.braziliancovid19.model.domain.symptoms.SymptomsAdapter
import com.lucasprojects.braziliancovid19.ui.viewmodel.MainViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val mBinding get() = _binding!!
    private val mMainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setObservesUI()
        setupRecyclerViewSymptoms()
        return mBinding.root
    }

    private fun setObservesUI() {
        mMainViewModel.mListData.observe(viewLifecycleOwner, {
            mBinding.textDataConfirmed.text = it.sumOf { n -> n.confirmeds }.toString()
            mBinding.textDataDeaths.text = it.sumOf { n -> n.deaths }.toString()
        })
    }

    private fun setupRecyclerViewSymptoms() {
        mBinding.recyclerSymptoms.apply {
            layoutManager = LinearLayoutManager(mBinding.root.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = SymptomsAdapter(getListSysmptoms())
        }
    }

    private fun getListSysmptoms() = listOf(
        Symptoms(R.drawable.ic_fever, getString(R.string.fever)),
        Symptoms(R.drawable.ic_cough, getString(R.string.cough)),
        Symptoms(R.drawable.ic_fatigue, getString(R.string.fatigue)),
        Symptoms(R.drawable.ic_pain, getString(R.string.pain)),
        Symptoms(R.drawable.ic_sore_throat, getString(R.string.sore_throat)),
        Symptoms(R.drawable.ic_diarrhoea, getString(R.string.diarrhoea)),
        Symptoms(R.drawable.ic_conjuctivitis, getString(R.string.conjuctivitis)),
        Symptoms(R.drawable.ic_loss_of_sense_of_taste, getString(R.string.loss_taste)),
        Symptoms(R.drawable.ic_loss_of_sense_of_smell, getString(R.string.loss_smell))
    )

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}