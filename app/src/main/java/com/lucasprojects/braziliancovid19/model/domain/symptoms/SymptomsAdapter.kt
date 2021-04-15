package com.lucasprojects.braziliancovid19.model.domain.symptoms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucasprojects.braziliancovid19.databinding.ItemSymptomsRecyclerBinding

class SymptomsAdapter(private val _listSymptoms: List<Symptoms>) : RecyclerView.Adapter<SymptomsAdapter.SymptomsViewHolder>() {

    inner class SymptomsViewHolder(private val itemBinding: ItemSymptomsRecyclerBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindData(symptoms: Symptoms) {
            itemBinding.imageSymptoms.setImageResource(symptoms.image)
            itemBinding.textNameSymptoms.text = symptoms.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : SymptomsViewHolder {
        val itemBinding = ItemSymptomsRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SymptomsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SymptomsViewHolder, position: Int) {
        holder.bindData(_listSymptoms[position])
    }

    override fun getItemCount() = _listSymptoms.size
}