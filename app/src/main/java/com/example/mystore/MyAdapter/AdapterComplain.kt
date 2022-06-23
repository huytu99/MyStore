package com.example.mystore.MyAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mystore.R

class AdapterComplain(val list:ArrayList<ModelComplain>):RecyclerView.Adapter<AdapterComplain.MyViewHolder>() {
    class MyViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val txttensp:TextView=view.findViewById(R.id.spcomplain)
        val txtphanhoi:TextView=view.findViewById(R.id.txtcomplain)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.layout_complain,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item=list[position]
        holder.txttensp.text=item.ten
        holder.txtphanhoi.text=item.phanhoi
    }

    override fun getItemCount(): Int {
        return list.size
    }

}