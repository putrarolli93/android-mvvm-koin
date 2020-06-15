package com.example.testapp.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.model.Provinsi
import kotlinx.android.synthetic.main.layout_item_provinsi.view.*


class ProvinsiListViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    fun bind(item: Provinsi) {
        view.apply {
            tvTitle.text = item.nama
            tvComment.text = "id : " + item.id.toString()
        }
    }

}