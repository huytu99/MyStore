package com.example.mystore.Admin

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystore.MainActivity.TrangChu
import com.example.mystore.MyAdapter.AdapterListSP
import com.example.mystore.R

class ListSP : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var viewManager = LinearLayoutManager(this)
    private lateinit var adapterListSP:AdapterListSP
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blankfragment)
        val actionBar=supportActionBar
        actionBar?.title="Danh Sách Sản Phẩm"
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FE724C")))
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        recyclerView=findViewById(R.id.rcview)
        adapterListSP= AdapterListSP(StoreManager.listSP)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=viewManager
        recyclerView.adapter=adapterListSP
    }
    @Override
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}