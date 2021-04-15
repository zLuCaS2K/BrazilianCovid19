package com.lucasprojects.braziliancovid19.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucasprojects.braziliancovid19.databinding.FragmentStatesListBinding
import com.lucasprojects.braziliancovid19.model.domain.data.Data
import com.lucasprojects.braziliancovid19.model.domain.data.DataAdapter
import com.lucasprojects.braziliancovid19.ui.activities.MainActivityViewModel

class StatesListFragment : Fragment() {

    private lateinit var mViewRoot: View
    private var _binding: FragmentStatesListBinding? = null
    private val binding get() = _binding!!
    private val mMainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
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
        Toast.makeText(mViewRoot.context, "${data.name}", Toast.LENGTH_SHORT).show()
    }
}