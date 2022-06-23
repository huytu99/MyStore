package com.example.mystore.Admin

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystore.MainActivity.TrangChu
import com.example.mystore.MyAdapter.AdapterComplain
import com.example.mystore.MyAdapter.AdapterListSP
import com.example.mystore.R

class Complain : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapterComplain: AdapterComplain
    var viewManager=LinearLayoutManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blankfragment)
        val actionBar=supportActionBar
        actionBar?.title="Phản Hồi Của Khách Hàng"
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FE724C")))
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        recyclerView=findViewById(R.id.rcview)
        adapterComplain= AdapterComplain(TrangChu.listComplain)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=viewManager
        recyclerView.adapter=adapterComplain
    }
    @Override
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}