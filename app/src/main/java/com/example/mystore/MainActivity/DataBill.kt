package com.example.mystore.MainActivity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.mystore.R
import kotlinx.android.synthetic.main.hoadonkhachhang.*
import java.util.*

class DataBill : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chitietdonhang)
        val actionBar = supportActionBar
        actionBar?.title = "Chi tiết đơn hàng"
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FE724C")))
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        // Ánh xạ dữ liệu
        val ten: TextView = findViewById(R.id.hotenbill)
        val sdt: TextView = findViewById(R.id.phonebill)
        val dc1: TextView = findViewById(R.id.addrbill1)
        val dc2: TextView = findViewById(R.id.addrbill2)
        val maso: TextView = findViewById(R.id.maBill)
        val hinhthuc: TextView = findViewById(R.id.hinhthucBill)
        val uudaiBill: TextView =findViewById(R.id.uudaiBill)
        val phiship:TextView=findViewById(R.id.feeshipBill)
        val tong:TextView=findViewById(R.id.tongBill)
        val trangthai:TextView=findViewById(R.id.trangthaiBill)
        val thoihan:TextView=findViewById(R.id.thoihanBill)
        //Nhận dữ liệu
        val bundle: Bundle? = intent.extras
        val name=bundle!!.getString("tenData")
        ten.text=name
        val phone=bundle.getString("phoneData")
        sdt.text=phone
        val addr1=bundle.getString("diachi1Data")
        dc1.text=addr1
        val addr2=bundle.getString("diachi2Data")
        dc2.text=addr2
        val id=bundle.getString("idData")
        maso.text=id
        val thanhtoan=bundle.getString("thanhtoanData")
        hinhthuc.text=thanhtoan
        val uudai=bundle.getString("uudaiData")
        uudaiBill.text=uudai
        val phi=bundle.getString("phishipData")
        phiship.text=phi
        val tongtien=bundle.getString("tongData")
        tong.text=tongtien
        val random= Random()
        val so=1+random.nextInt(4)
        thoihan.text=so.toString()
        val list= listOf("Đang chuẩn bị","Đang giao hàng")
            if(so%2 == 0){
                trangthai.text=list[0]
            }
            else{
                trangthai.text=list[1]
            }
        returnMain.setOnClickListener {
            startActivity((Intent(this,TrangChu::class.java)))
        }
    }
    @Override
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}