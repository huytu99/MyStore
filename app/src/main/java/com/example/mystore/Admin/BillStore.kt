package com.example.mystore.Admin

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystore.MainActivity.TrangChu
import com.example.mystore.MyAdapter.AdapterDataList
import com.example.mystore.R

class BillStore : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var viewManager = LinearLayoutManager(this)
    private lateinit var adapterBill:AdapterDataList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blankfragment)
        val actionBar=supportActionBar
        actionBar?.title="Danh Sách Đơn Hàng"
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FE724C")))
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        recyclerView=findViewById(R.id.rcview)
        adapterBill=AdapterDataList(TrangChu.bill)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=viewManager
        recyclerView.adapter=adapterBill
    }
    @Override
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}