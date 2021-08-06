package com.lucasprojects.braziliancovid19.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.lucasprojects.braziliancovid19.R
import com.lucasprojects.braziliancovid19.databinding.BottomSheetDetailsStatesBinding
import com.lucasprojects.braziliancovid19.databinding.FragmentStatesListBinding
import com.lucasprojects.braziliancovid19.model.domain.data.Data
import com.lucasprojects.braziliancovid19.model.domain.data.DataAdapter
import com.lucasprojects.braziliancovid19.ui.viewmodel.MainViewModel
import com.lucasprojects.braziliancovid19.utils.Utils

class StatesListFragment : Fragment() {

    private var _binding: FragmentStatesListBinding? = null
    private val mBinding get() = _binding!!
    private val mMainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
        _binding = FragmentStatesListBinding.inflate(layoutInflater, container, false)
        setObservesUI()
        return mBinding.root
    }

    private fun setObservesUI() {
        mMainViewModel.mListData.observe(viewLifecycleOwner, {
            setupRecyclerStates(it)
        })
    }

    private fun setupRecyclerStates(listStates: List<Data>) {
        mBinding.recyclerStates.apply {
            layoutManager = LinearLayoutManager(mBinding.root.context)
            adapter = DataAdapter(listStates, this@StatesListFragment::onClickState)
            visibility = View.VISIBLE
        }
    }

    private fun onClickState(data: Data) {
        showBottomSheetDetailsState(data)
    }

    private fun showBottomSheetDetailsState(data: Data) {
        val bottomSheetDialog = BottomSheetDialog(mBinding.root.context, R.style.BottomSheetDialogTheme)
        val bottomSheetBinding = BottomSheetDetailsStatesBinding.inflate(LayoutInflater.from(activity))
        bottomSheetBinding.apply {
            this.textConfirmedBottomSheet.text = Utils.formatNumberData(data.confirmeds)
            this.textDeathsBottomSheet.text = Utils.formatNumberData(data.deaths)
            this.textPopulationBottomSheet.text = Utils.formatNumberData(data.population)
            this.textDeathPercentBottomSheet.text = Utils.formatDeathPercent(data.deathRate.toDouble())
        }
        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        bottomSheetDialog.show()
    }
}