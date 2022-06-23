package com.example.mystore.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystore.MyAdapter.AdapterDataList
import com.example.mystore.R

class DataCustomer : Fragment() {
    companion object{
        lateinit var recyclerView: RecyclerView
        lateinit var adapterDataList: AdapterDataList
        lateinit var txtThongbao:TextView
    }
    private var layoutManager= LinearLayoutManager(context)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title=" Hóa Đơn Đã Thanh Toán"
        (activity as AppCompatActivity).supportActionBar?.setLogo(R.drawable.iconlistbill)
        val view= inflater.inflate(R.layout.activity_data_customer, container, false)
        // Ánh xạ
        recyclerView=view.findViewById(R.id.rcdatacustomer)
        txtThongbao=view.findViewById(R.id.chuacohoadon)
        adapterDataList=AdapterDataList(TrangChu.bill)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter=adapterDataList
        if(TrangChu.bill.size<=0){
            adapterDataList.notifyDataSetChanged()
            recyclerView.isVisible=false
            txtThongbao.isVisible=true
        }
        else{
            adapterDataList.notifyDataSetChanged()
            recyclerView.isVisible=true
            txtThongbao.isVisible=false
        }
        return view
    }
}