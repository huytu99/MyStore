package com.example.mystore.MyAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mystore.Admin.StoreManager
import com.example.mystore.MainActivity.TrangChu
import com.example.mystore.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.DecimalFormat

class AdapterItemBill(private val arrayList:ArrayList<GioHang>) :RecyclerView.Adapter<AdapterItemBill.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tenBill: TextView =view.findViewById(R.id.tenbill)
        val soluongBill: TextView =view.findViewById(R.id.soluongbill)
        val giaBill:TextView=view.findViewById(R.id.giabill)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.itembill,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = arrayList[position]
        holder.tenBill.text = item.name
        val decimalFormat = DecimalFormat("###,###,###")
        holder.giaBill.text = decimalFormat.format(item.gia) + "đ"
        holder.soluongBill.text = item.soluong.toString()
        TrangChu.bill2.add(BillKhac(holder.tenBill.text.toString(),holder.soluongBill.text.toString()))
        var database:DatabaseReference = FirebaseDatabase.getInstance().reference
        database.child("SanPham").push().setValue(SanPham(holder.tenBill.text.toString(),holder.soluongBill.text.toString(),holder.giaBill.text.toString()))
        StoreManager.listSP.add(ListSanPham(holder.tenBill.text.toString(),holder.soluongBill.text.toString(),holder.giaBill.text.toString()))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}