package com.example.mystore.MyAdapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystore.MainActivity.DataBill
import com.example.mystore.MainActivity.DataCustomer
import com.example.mystore.MainActivity.DetailItem
import com.example.mystore.MainActivity.TrangChu
import com.example.mystore.R

class AdapterDataList(private val list:ArrayList<ModelBill>):RecyclerView.Adapter<AdapterDataList.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val idData: TextView =view.findViewById(R.id.idData)
        val tenData: TextView =view.findViewById(R.id.tenData)
        val thanhtoanData: TextView =view.findViewById(R.id.thanhtoanData)
        val phishipData: TextView =view.findViewById(R.id.phishipData)
        val tongData: TextView =view.findViewById(R.id.tongData)
        val phoneData:TextView=view.findViewById(R.id.phoneData)
        val diachi1Data:TextView=view.findViewById(R.id.diachi1Data)
        val diachi2Data:TextView=view.findViewById(R.id.diachi2Data)
        val uudaiData:TextView=view.findViewById(R.id.uudaiData)
        val btnHuyDon:Button=view.findViewById(R.id.btnHuyDonHang)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.bill_list,parent,false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item=list[position]
        holder.idData.text=item.maso.toString()
        holder.tenData.text=item.hoten
        holder.thanhtoanData.text=item.hinhthuc
        holder.phishipData.text=item.phiship
        holder.tongData.text=item.tongtien
        holder.phoneData.text=item.phone
        holder.diachi1Data.text=item.diachi1
        holder.diachi2Data.text=item.diachi2
        holder.uudaiData.text=item.uudai
        holder.itemView.setOnClickListener {
            val i = Intent(it.context, DataBill::class.java)
            i.putExtra("idData",item.maso.toString())
            i.putExtra("tenData",item.hoten)
            i.putExtra("thanhtoanData",item.hinhthuc)
            i.putExtra("phishipData",item.phiship)
            i.putExtra("tongData",item.tongtien)
            i.putExtra("phoneData",item.phone)
            i.putExtra("diachi1Data",item.diachi1)
            i.putExtra("diachi2Data",item.diachi2)
            i.putExtra("uudaiData",item.uudai)
            val bundle=Bundle()
            i.putExtras(bundle)
            it.context.startActivity(i)
        }
        holder.btnHuyDon.setOnClickListener {
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle("THÔNG BÁO")
            builder.setMessage("Đơn hàng của bạn sẽ bị xóa"+"\n"+"Bạn chắc chứ?")
                .setNegativeButton("YES", DialogInterface.OnClickListener{
                        dialog, id -> run {
                    TrangChu.bill.remove(item)
                    TrangChu.bill2.removeAt(position)
                    }
                    notifyDataSetChanged()
                    if(TrangChu.bill.size<=0){
                        DataCustomer.adapterDataList.notifyDataSetChanged()
                        DataCustomer.recyclerView.isVisible = false
                        DataCustomer.txtThongbao.isVisible = true
                    }
                })
                .setPositiveButton("NO",DialogInterface.OnClickListener{
                        dialog, id -> dialog.cancel()
                })
                .show()
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}