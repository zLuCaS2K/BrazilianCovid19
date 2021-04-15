package com.lucasprojects.braziliancovid19.model.domain.data

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.lucasprojects.braziliancovid19.R
import com.lucasprojects.braziliancovid19.databinding.ItemDataRecyclerBinding
import com.lucasprojects.braziliancovid19.utils.Utils
import com.squareup.picasso.Picasso

class DataAdapter(
    private val _listStates: List<Data>,
    private val _callback: (Data) -> Unit,
) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    inner class DataViewHolder(private val itemBinding: ItemDataRecyclerBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindData(data: Data) {
            Picasso.get().load("https://devarthurribeiro.github.io/covid19-brazil-api/static/flags/${data.uf}.png").into(itemBinding.imageFlagState)
            itemBinding.textNameState.text = Utils.setNameState(data.uf)
            itemBinding.textNameState.text = Utils.formatDate(data.date)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemBinding = ItemDataRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val dataViewHolder = DataViewHolder(itemBinding)
        dataViewHolder.itemView.setOnClickListener {
            _callback(_listStates[dataViewHolder.adapterPosition])
        }
        return dataViewHolder
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.itemView.findViewById<MaterialCardView>(R.id.cardState).animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.fade_transition)
        holder.bindData(_listStates[position])
    }

    override fun getItemCount() = _listStates.size
}