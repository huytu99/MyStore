package com.example.mystore.MyAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mystore.R
import java.text.DecimalFormat

class AdapterListSP(private val arrayList:ArrayList<ListSanPham>) : RecyclerView.Adapter<AdapterListSP.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tenBill: TextView =view.findViewById(R.id.tenbill)
        val soluongBill: TextView =view.findViewById(R.id.soluongbill)
        val giaBill: TextView =view.findViewById(R.id.giabill)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_liststore,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = arrayList[position]
        holder.tenBill.text = item.tenSP
        holder.giaBill.text=item.giaSP
        holder.soluongBill.text = item.soluongSP
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}