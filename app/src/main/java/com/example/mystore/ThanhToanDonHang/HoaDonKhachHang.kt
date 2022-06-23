package com.example.mystore.ThanhToanDonHang

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystore.Admin.StoreManager
import com.example.mystore.MainActivity.DataBill
import com.example.mystore.MainActivity.TrangChu
import com.example.mystore.MyAdapter.AdapterItemBill
import com.example.mystore.MyAdapter.ModelBill
import com.example.mystore.R
import com.example.mystore.ShoppingCart.Cart
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.DecimalFormat
import java.util.*

class HoaDonKhachHang : AppCompatActivity() {
    private lateinit var database:DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private var viewManager = LinearLayoutManager(this)
    private lateinit var adapterBill: AdapterItemBill
    lateinit var hotenBill:TextView
    lateinit var phoneBill:TextView
    lateinit var diachi1:TextView
    lateinit var diachi2:TextView
    lateinit var masoBill:TextView
    lateinit var hinhthucBill:TextView
    lateinit var uudaiBill:TextView
    lateinit var returnMain:Button
    lateinit var phiShip:TextView
    lateinit var tongBill:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hoadonkhachhang)
        val actionBar=supportActionBar
        actionBar?.title="Hóa Đơn Khách Hàng"
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FE724C")))
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        // Ánh xạ dữ liệu
        hotenBill=findViewById(R.id.hotenbill)
        phoneBill=findViewById(R.id.phonebill)
        diachi1=findViewById(R.id.addrbill1)
        diachi2=findViewById(R.id.addrbill2)
        masoBill=findViewById(R.id.maBill)
        hinhthucBill=findViewById(R.id.hinhthucBill)
        uudaiBill=findViewById(R.id.uudaiBill)
        tongBill=findViewById(R.id.tongBill)
        returnMain=findViewById(R.id.returnMain)
        phiShip=findViewById(R.id.feeshipBill)
        //Nhận dữ liệu được truyền từ Info
        val bundle:Bundle?=intent.extras
        val hoten=bundle!!.getString("hovaten")
        hotenBill.text=hoten
        val phone=bundle.getString("sdt")
        phoneBill.text=phone
        //Set tổng tiền, ưu đãi
        if(ThongTinKhacHang.btncard.isChecked){
            val hinhthuc=bundle.getString("card")
            hinhthucBill.text=hinhthuc
            val uudai=bundle.getString("uudai1")
            uudaiBill.text=uudai
            phiShip.text="Không"
            tongBill.text=Cart.tongdonhang.text
        }
        if(ThongTinKhacHang.btnatm.isChecked){
            val hinhthuc=bundle.getString("atm")
            hinhthucBill.text=hinhthuc
            val uudai=bundle.getString("uudai2")
            uudaiBill.text=uudai
            var TongTien:Long=0
            for(i in 0 until TrangChu.list.size){
                TongTien+=TrangChu.list[i].gia
            }
            val phi=TongTien * 1 / 100
            val tong=TongTien * 91 / 100
            val tienuudai=TongTien * 9 / 100
            StoreManager.uudai.add(tienuudai)
            val decimalFormat=DecimalFormat("###,###,###")
            phiShip.text=decimalFormat.format(phi)+"đ"
            tongBill.text=decimalFormat.format(tong)+"đ"
        }
        val random=Random()
        val maso= 1000 + random.nextInt(9000)
        masoBill.text=maso.toString()
        diachi1.text="Số: "+DialogInfo.diachi.text.toString()+", Phường: "+DialogInfo.phuong.text.toString()
        diachi2.text="Quận: "+DialogInfo.quan.text.toString()+", Thành phố/Tỉnh: "+DialogInfo.thanhpho.text.toString()

        // Đổ Adapter
        recyclerView=findViewById(R.id.rcBill)
        recyclerView.layoutManager=viewManager
        recyclerView.setHasFixedSize(true)
        adapterBill=AdapterItemBill(TrangChu.list)
        recyclerView.adapter=adapterBill
        //Button quay lai trang chu
        returnMain.setOnClickListener {
            database=FirebaseDatabase.getInstance().reference
            database.child("HoaDon").push().setValue(ModelBill(hotenBill.text.toString(),phoneBill.text.toString(),diachi1.text.toString()
                ,diachi2.text.toString(),masoBill.text.toString().toInt(), hinhthucBill.text.toString()
                ,uudaiBill.text.toString(),phiShip.text.toString(),tongBill.text.toString()))
            TrangChu.bill.add(ModelBill(hotenBill.text.toString(),phoneBill.text.toString(),diachi1.text.toString()
                        ,diachi2.text.toString(),masoBill.text.toString().toInt(), hinhthucBill.text.toString()
                        ,uudaiBill.text.toString(),phiShip.text.toString(),tongBill.text.toString()))
            TrangChu.list.clear()
            startActivity(Intent(this,TrangChu::class.java))
        }
    }
    @Override
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}