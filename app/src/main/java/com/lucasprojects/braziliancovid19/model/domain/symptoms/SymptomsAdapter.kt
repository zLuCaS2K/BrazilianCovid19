package com.lucasprojects.braziliancovid19.model.domain.symptoms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lucasprojects.braziliancovid19.R

class SymptomsAdapter(private val listSymptoms: List<Symptoms>) : RecyclerView.Adapter<SymptomsAdapter.SymptomsViewHolder>() {

    inner class SymptomsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(symptoms: Symptoms) {
            itemView.findViewById<ImageView>(R.id.imageSymptoms).setImageResource(symptoms.image)
            itemView.findViewById<TextView>(R.id.textNameSymptoms).text = symptoms.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SymptomsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_symptoms_recycler, parent, false))

    override fun onBindViewHolder(holder: SymptomsViewHolder, position: Int) {
        holder.bindData(listSymptoms[position])
    }

    override fun getItemCount() = listSymptoms.size
}