package com.lucasprojects.braziliancovid19.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.lucasprojects.braziliancovid19.R
import com.lucasprojects.braziliancovid19.databinding.FragmentHomeBinding
import com.lucasprojects.braziliancovid19.model.domain.symptoms.Symptoms
import com.lucasprojects.braziliancovid19.model.domain.symptoms.SymptomsAdapter
import com.lucasprojects.braziliancovid19.ui.activities.MainActivityViewModel
import com.lucasprojects.braziliancovid19.utils.Utils

class HomeFragment : Fragment() {

    private lateinit var mViewRoot: View
    private lateinit var mDialogLoading: Dialog
    private lateinit var mDialogAnError: Dialog
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val mMainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        mViewRoot = binding.root
        setupObservers()
        loadDataInDataStore()
        initializeAlertDialogs()
        setupRecyclerViewSymptoms()
        return mViewRoot
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding?.btnRefreshData?.setOnClickListener { mMainActivityViewModel.loadAllData(mViewRoot.context) }
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
        mMainActivityViewModel.mIsViewLoading.observe(viewLifecycleOwner, {
            if (it) mDialogLoading.show() else mDialogLoading.dismiss()
        })
        mMainActivityViewModel.mAnErrorOccurred.observe(viewLifecycleOwner, {
            if (it) {
                mDialogAnError.show()
                mDialogAnError.findViewById<MaterialButton>(R.id.btnTryAgain).setOnClickListener {
                    mDialogAnError.dismiss()
                    mMainActivityViewModel.loadAllData(mViewRoot.context)
                }
            } else {
                mDialogAnError.dismiss()
            }
        })
    }

    private fun loadDataInDataStore() {
        mMainActivityViewModel.loadDataStore()
    }

    private fun initializeAlertDialogs() {
        mDialogLoading = Utils.showAlertDialog(mViewRoot.context, 1)
        mDialogAnError = Utils.showAlertDialog(mViewRoot.context, 2)
    }

    private fun setupRecyclerViewSymptoms() {
        binding.recyclerSymptoms.apply {
            layoutManager = LinearLayoutManager(mViewRoot.context,LinearLayoutManager.HORIZONTAL ,false)
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