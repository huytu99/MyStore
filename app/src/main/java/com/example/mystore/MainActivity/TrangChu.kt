package com.example.mystore.MainActivity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.mystore.Admin.Login
import com.example.mystore.Admin.StoreManager
import com.example.mystore.Airpods.Airpods
import com.example.mystore.DienThoai.DienThoai
import com.example.mystore.Ipad.Ipad
import com.example.mystore.LapTop.LapTop
import com.example.mystore.MyAdapter.*
import com.example.mystore.R
import com.example.mystore.ShoppingCart.Cart
import kotlinx.android.synthetic.main.activity_main.*

class TrangChu : AppCompatActivity() {
    companion object{
         val list=ArrayList<GioHang>()
         val bill=ArrayList<ModelBill>()
         val bill2=ArrayList<BillKhac>()
         val listComplain=ArrayList<ModelComplain>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val actionBar = supportActionBar
        actionBar?.setLogo(R.drawable.rsz_1logo)
        actionBar?.setDisplayUseLogoEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FE724C")))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dienthoai.setOnClickListener {
                startActivity(Intent(this@TrangChu, DienThoai::class.java))
        }
        airpod.setOnClickListener {
                startActivity(Intent(this@TrangChu, Airpods::class.java))
        }
        laptop.setOnClickListener {
                startActivity(Intent(this@TrangChu, LapTop::class.java))
        }
        ipad.setOnClickListener {
                startActivity(Intent(this@TrangChu, Ipad::class.java))

        }
        selectbottom.selectedItemId=R.id.home
        selectbottom.setOnNavigationItemSelectedListener {
        when(it.itemId) {
                    R.id.hot -> {
                        supportFragmentManager.commit {
                            setReorderingAllowed(true)
                            replace<DataCustomer>(R.id.mainfrag)
                        }
                       true
                    }
                    R.id.giohang -> {
                        supportFragmentManager.commit {
                            setReorderingAllowed(true)
                            replace<Cart>(R.id.mainfrag)
                        }
                        true
                    }
                    else -> {
                        startActivity(Intent(this,TrangChu::class.java))
                        true
                    }
                }
            }
        Log.d("BBB", list.toString())
        Log.d("BBB", bill.toString())
        Log.d("BBB", listComplain.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_info,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.showinfo->{
                startActivity(Intent(this,Login::class.java))
            }
        }
        return true
    }
}