package com.example.mystore.ThanhToanDonHang

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.mystore.MainActivity.DataBill
import com.example.mystore.MainActivity.TrangChu
import com.example.mystore.R

open class ThongTinKhacHang : AppCompatActivity() {
    companion object{
        lateinit var hovaten:EditText
        lateinit var numberPhone:EditText
        lateinit var btncard:RadioButton
        lateinit var btnatm:RadioButton
        lateinit var diachi1:TextView
        lateinit var diachi2:TextView
    }
    private lateinit var diachi:LinearLayout
    private lateinit var btnxacnhan:Button
    private lateinit var btnkiemtra:Button
    private lateinit var radioGroup: RadioGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info)
        val actionBar=supportActionBar
        actionBar?.title="Thông Tin Khách Hàng"
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FE724C")))
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        //Ánh xạ dữ liệu
        diachi1=findViewById(R.id.diachi1)
        diachi2=findViewById(R.id.diachi2)
        diachi=findViewById(R.id.address)
        hovaten=findViewById(R.id.hoten)
        numberPhone=findViewById(R.id.phonenumber)
        btnxacnhan=findViewById(R.id.xacnhaninfo)
        btnkiemtra=findViewById(R.id.kiemtraCart)
        btncard=findViewById(R.id.btncard)
        btnatm=findViewById(R.id.btnatm)
        radioGroup=findViewById(R.id.radioGroup)
        //Hiển thị dialog Địa chỉ
        diachi.setOnClickListener {
            val dialogInfo= DialogInfo()
            dialogInfo.show(supportFragmentManager,"AAA")
        }
        //Kiểm tra thông tin
        btnxacnhan.setOnClickListener {
            val s:String= numberPhone.text.toString()
            var i=0
            i=s.indexOf("0")
            if(hovaten.text.isEmpty()|| numberPhone.text.isEmpty()|| diachi1.text.isEmpty()|| diachi2.text.isEmpty()){
                val builder=AlertDialog.Builder(this)
                builder.setTitle("THÔNG BÁO")
                    .setMessage("Vui lòng nhập đầy đủ thông tin")
                    .setPositiveButton("OK",DialogInterface.OnClickListener{
                            dialog, which -> dialog.cancel()
                    })
                    .show()
            }
            else if(numberPhone.text.length!=10 || i!=0){
                val builder=AlertDialog.Builder(this)
                builder.setTitle("THÔNG BÁO")
                    .setMessage("Số điện thoại không đúng")
                    .setPositiveButton("OK",DialogInterface.OnClickListener{
                            dialog, which -> dialog.cancel()
                    })
                    .show()
                numberPhone.text.clear()
            }
            else if((!btncard.isChecked)&&(!btnatm.isChecked)){
                val builder=AlertDialog.Builder(this)
                builder.setTitle("THÔNG BÁO")
                    .setMessage("Vui lòng chọn hình thức thanh toán")
                    .setPositiveButton("OK",DialogInterface.OnClickListener{
                            dialog, which -> dialog.cancel()
                    })
                    .show()
            }
            else {
                val intent=Intent(this,HoaDonKhachHang::class.java)
                intent.putExtra("hovaten", hovaten.text.toString())
                intent.putExtra("sdt", numberPhone.text.toString())
                if(btncard.isChecked){
                    intent.putExtra("card","Tiền mặt")
                    intent.putExtra("uudai1","Freeship")
                }
                if(btnatm.isChecked){
                    intent.putExtra("atm","Thẻ tín dụng")
                    intent.putExtra("uudai2","Giảm 10% tổng bill")
                }
                startActivity(intent)
            }
        }
        //Set button Huy xac nhan thong tin
        btnkiemtra.setOnClickListener {
            onBackPressed()
        }
    }
    @Override
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}