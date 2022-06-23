package com.example.mystore.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.mystore.MainActivity.TrangChu
import com.example.mystore.MyAdapter.ListSanPham
import com.example.mystore.R
import java.text.DecimalFormat

class StoreManager : AppCompatActivity() {
    companion object{
        var uudai   = arrayListOf<Long>()
        val listSP=ArrayList<ListSanPham>()
    }
    lateinit var btndangxuat:Button
    lateinit var sodon:TextView
    lateinit var tongdoanhthu:TextView
    lateinit var itemStore:TextView
    lateinit var detailStore:TextView
    lateinit var complainStore:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_manager)
        val actionBar = supportActionBar
        actionBar?.hide()
        sodon=findViewById(R.id.sodon)
        tongdoanhthu=findViewById(R.id.tongdoanhthu)
        itemStore=findViewById(R.id.itemStore)
        detailStore=findViewById(R.id.detailStore)
        complainStore=findViewById(R.id.complainStore)
        btndangxuat=findViewById(R.id.btndangxuat)
        sodon.text=TrangChu.bill.size.toString()+" "+"đơn"
        var tongtien:Long=0
        for(i in 0 until TrangChu.bill.size){
            var x=TrangChu.bill[i].tongtien.replace(",","")
            var y=x.replace("đ","")
            tongtien+=y.toLong()
        }
        val decimalFormat = DecimalFormat("###,###,###")
        tongdoanhthu.text=decimalFormat.format(tongtien)+"đ"
        itemStore.setOnClickListener {
            startActivity(Intent(this,ListSP::class.java))
        }
        detailStore.setOnClickListener {
            startActivity(Intent(this,BillStore::class.java))
        }
        btndangxuat.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
        }
        complainStore.setOnClickListener {
            startActivity(Intent(this,Complain::class.java))
        }
    }
}