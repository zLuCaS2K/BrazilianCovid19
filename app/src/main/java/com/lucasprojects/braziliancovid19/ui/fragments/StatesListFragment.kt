package com.lucasprojects.braziliancovid19.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.lucasprojects.braziliancovid19.R
import com.lucasprojects.braziliancovid19.databinding.FragmentStatesListBinding
import com.lucasprojects.braziliancovid19.model.domain.data.Data
import com.lucasprojects.braziliancovid19.model.domain.data.DataAdapter
import com.lucasprojects.braziliancovid19.ui.activities.MainActivityViewModel
import com.lucasprojects.braziliancovid19.utils.Utils

class StatesListFragment : Fragment() {

    private lateinit var mViewRoot: View
    private var _binding: FragmentStatesListBinding? = null
    private val binding get() = _binding!!
    private val mMainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentStatesListBinding.inflate(layoutInflater, container, false)
        mViewRoot = binding.root
        setupObservers()
        return mViewRoot
    }

    private fun setupObservers() {
        mMainActivityViewModel.mListData.observe(viewLifecycleOwner, {
            setupRecyclerStates(it)
        })
    }

    private fun setupRecyclerStates(listStates: List<Data>) {
        binding.recyclerStates.apply {
            layoutManager = LinearLayoutManager(mViewRoot.context)
            adapter = DataAdapter(listStates, this@StatesListFragment::onClickState)
            visibility = View.VISIBLE
        }
    }

    private fun onClickState(data: Data) {
        showBottomSheetDetailsState(data)
    }

    private fun showBottomSheetDetailsState(data: Data) {
        val bottomSheetDialog = BottomSheetDialog(mViewRoot.context, R.style.BottomSheetDialogTheme)
        val bottomSheetView = LayoutInflater.from(mViewRoot.context).inflate(R.layout.bottom_sheet_details_states, bottomSheetDialog.findViewById(R.id.containerBottomSheetDetailsStates))
        bottomSheetView.findViewById<TextView>(R.id.textConfirmedBottomSheet).text = Utils.formatNumberData(data.confirmeds!!.toInt())
        bottomSheetView.findViewById<TextView>(R.id.textDeathsBottomSheet).text = Utils.formatNumberData(data.deaths!!.toInt())
        bottomSheetView.findViewById<TextView>(R.id.textPopulationBottomSheet).text = Utils.formatNumberData(data.population!!.toInt())
        bottomSheetView.findViewById<TextView>(R.id.textDeathPercentBottomSheet).text = Utils.formatDeathPercent(data.deathRate?.toDouble())
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }
}