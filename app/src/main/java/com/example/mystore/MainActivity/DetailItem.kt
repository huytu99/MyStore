package com.example.mystore.MainActivity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.bumptech.glide.Glide
import com.example.mystore.MyAdapter.GioHang
import com.example.mystore.R
import com.example.mystore.ShoppingCart.Cart
import kotlinx.android.synthetic.main.detail_item.*
import java.text.DecimalFormat

class DetailItem : AppCompatActivity() {
    companion object{
        lateinit var tenSP:TextView
        lateinit var soton:TextView
        lateinit var themGioHang:Button
        lateinit var spinner1: Spinner
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_item)
        val actionBar = supportActionBar
        actionBar?.title = "Chi Tiết Sản Phẩm"
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FE724C")))
        // Ánh xạ dữ liệu
        tenSP= findViewById(R.id.tenDetail)
        val giahang: TextView = findViewById(R.id.giaDetail)
        val img: ImageView = findViewById(R.id.imgDetail)
        val info1: TextView = findViewById(R.id.infodetail1)
        val info2: TextView = findViewById(R.id.infodetail2)
        val imgdetail: ImageView = findViewById(R.id.imgDetail1)
        //Nhận dữ liệu
        val bundle: Bundle? = intent.extras
        val name = bundle!!.getString("name")
        val gia = bundle.getString("gia")
        val imgItem = bundle.getString("img")
        val infoDetail1 = bundle.getString("info1")
        val infoDetail2 = bundle.getString("info2")
        val imgDetail = bundle.getString("imgdetail")
        val hangtonkho=bundle.getString("hangton")
        soton=findViewById(R.id.hangton)
        spinner1=findViewById(R.id.spinner)
        tenSP.text = name
        val decimalFormat: DecimalFormat = DecimalFormat("###,###,###")
        giahang.text = decimalFormat.format(gia.toString().toLong())
        info1.text = infoDetail1
        info2.text = infoDetail2
        soton.text=hangtonkho
        Glide.with(application).load(imgDetail).into(imgdetail)
        Glide.with(application).load(imgItem).into(img)
        SetSpinner()
        themGioHang=findViewById(R.id.addGiohang)
        themGioHang.setOnClickListener {
            if(soton.text.toString().toInt()<=0){
                val builder=AlertDialog.Builder(this)
                builder.setTitle("THÔNG BÁO")
                    .setMessage("Sản phẩm đã hết hàng")
                    .setPositiveButton("OK",DialogInterface.OnClickListener{
                            dialog, which ->
                        run {
                            dialog.cancel()
                            onBackPressed()
                        }
                    })
                    .show()
            }
            if(spinner1.selectedItem==0){
                val builder=AlertDialog.Builder(this)
                builder.setTitle("THÔNG BÁO")
                    .setMessage("Vui lòng chọn số lượng sản phẩm")
                    .setPositiveButton("OK",DialogInterface.OnClickListener{
                            dialog, which ->
                        run {
                            dialog.cancel()
                            onBackPressed()
                        }
                    })
                    .show()
            }
            if(TrangChu.list.size >0){
                var a : Boolean =false
                for(i in 0 until TrangChu.list.size) {
                        if (TrangChu.list[i].name==name.toString()) {
                            TrangChu.list[i].soluong = (TrangChu.list[i].soluong) + (spinner1.selectedItem.toString()).toInt()
                            if(TrangChu.list[i].soluong>TrangChu.list[i].hangton){
                                val builder=AlertDialog.Builder(this)
                                builder.setTitle("THÔNG BÁO")
                                    .setMessage("Không đủ số lượng")
                                    .setPositiveButton("OK",DialogInterface.OnClickListener{
                                            dialog, which ->
                                        run {
                                            dialog.cancel()
                                        }
                                    })
                                    .show()
                                TrangChu.list[i].soluong=TrangChu.list[i].hangton
                            }
                            soton.text=(hangtonkho!!.toInt()-TrangChu.list[i].soluong).toString()
                            if(soton.text.toString().toInt()<=0){
                                soton.text="0"
                            }
                            TrangChu.list[i].gia =
                                gia.toString().toLong()*TrangChu.list[i].soluong
                            Log.d("AAA",
                                GioHang(TrangChu.list[i].name+"",TrangChu.list[i].gia,hangtonkho!!.toInt(),imgItem.toString(),TrangChu.list[i].soluong).toString())
                            a=true
                        }
                }
                if(a==false){
                    var soM: Int = (spinner1.selectedItem.toString()).toInt()
                    if(soM>hangtonkho!!.toInt()){
                        val builder=AlertDialog.Builder(this)
                        builder.setTitle("THÔNG BÁO")
                            .setMessage("Không đủ số lượng")
                            .setPositiveButton("OK",DialogInterface.OnClickListener{
                                    dialog, which ->
                                run {
                                    dialog.cancel()
                                }
                            })
                            .show()
                        soM=hangtonkho!!.toInt()
                    }
                    soton.text=(hangtonkho!!.toInt()-soM).toString()
                    if(soton.text.toString().toInt()<=0){
                        soton.text="0"
                    }
                    val giaM: Long = soM * (gia.toString()).toLong()
                    TrangChu.list.add(GioHang(tenSP.text.toString(), giaM,hangtonkho!!.toInt(), imgItem.toString(), soM))
                }
            }
            else{
                var soM: Int = (spinner1.selectedItem.toString()).toInt()
                if(soM>hangtonkho!!.toInt()){
                    val builder=AlertDialog.Builder(this)
                    builder.setTitle("THÔNG BÁO")
                        .setMessage("Không đủ số lượng")
                        .setPositiveButton("OK",DialogInterface.OnClickListener{
                                dialog, which ->
                            run {
                                dialog.cancel()
                            }
                        })
                        .show()
                    soM=hangtonkho!!.toInt()
                }
                soton.text=(hangtonkho!!.toInt()-soM).toString()
                if(soton.text.toString().toInt()<=0){
                    soton.text="0"
                }
                val giaM: Long = soM * (gia.toString()).toLong()
                TrangChu.list.add(GioHang(tenSP.text.toString(), giaM,hangtonkho!!.toInt(), imgItem.toString(), soM))
            }
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<Cart>(R.id.detailfrag)
            }
        }
    }

    private fun SetSpinner() {
        val so = arrayOf(0,1, 2, 3, 4, 5)
        ArrayAdapter(this, android.R.layout.simple_spinner_item, so)
            .also {
                it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = it
            }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.muenu_complain,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.showcomplain ->{
                val dialog=ComplainFragment()
                dialog.show(supportFragmentManager,"BBB")
            }
            R.id.back ->{
                startActivity(Intent(this,TrangChu::class.java))
            }
        }
        return true
    }

}