package com.example.testapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.model.Provinsi

class ProvinsiListAdapter : RecyclerView.Adapter<ProvinsiListViewHolder>() {

    private var provinsiList = mutableListOf<Provinsi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinsiListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_provinsi, parent, false)
        return ProvinsiListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return provinsiList.count()
    }

    override fun onBindViewHolder(holder: ProvinsiListViewHolder, position: Int) {
        holder.bind(provinsiList[position])
    }

    fun updateList(list: MutableList<Provinsi> ) {
        this.provinsiList = list
        this.notifyDataSetChanged()
    }

}