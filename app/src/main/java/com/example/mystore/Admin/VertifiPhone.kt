package com.example.mystore.Admin

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.mystore.R

class  VertifiPhone : AppCompatActivity() {
    lateinit var btn:Button
    lateinit var edt:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertifi_phone)
        val actionBar=supportActionBar
        actionBar!!.hide()
        btn=findViewById(R.id.btntieptucphone)
        edt=findViewById(R.id.edtphone)
        val phoneDefault="0941678287"
        btn.setOnClickListener {
            if(edt.text.isEmpty()){
                val builder= AlertDialog.Builder(this)
                builder.setTitle("Thông Báo")
                    .setMessage("Yêu cầu nhập số điện thoại")
                    .setPositiveButton("OK", DialogInterface.OnClickListener{
                            dialog, which -> dialog.cancel()
                    })
                    .show()
            }
            else if(edt.text.toString() == phoneDefault){
                edt.text.clear()
                startActivity(Intent(this,VertifiCode::class.java))
            }
            else{
                val builder= AlertDialog.Builder(this)
                builder.setTitle("Thông Báo")
                    .setMessage("Số điện thoại không đúng")
                    .setPositiveButton("OK", DialogInterface.OnClickListener{
                            dialog, which -> dialog.cancel()
                    })
                    .show()
            }
        }
    }
}