package com.example.mystore.Admin

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.mystore.R

class VertifiCode : AppCompatActivity() {
    lateinit var btn:Button
    lateinit var edt:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertifi_code)
        val actionBar=supportActionBar
        actionBar!!.hide()
        btn=findViewById(R.id.btntieptuccode)
        edt=findViewById(R.id.edtxacthuc)
        val codeDefault="1234"
        btn.setOnClickListener {
            if(edt.text.isEmpty()){
                val builder= AlertDialog.Builder(this)
                builder.setTitle("Thông Báo")
                    .setMessage("Yêu cầu nhập mã xác thực")
                    .setPositiveButton("OK", DialogInterface.OnClickListener{
                            dialog, which -> dialog.cancel()
                    })
                    .show()
            }
            else if(edt.text.toString() == codeDefault){
                edt.text.clear()
                startActivity(Intent(this,StoreManager::class.java))
            }
            else {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Thông Báo")
                    .setMessage("Mã xác thực không đúng")
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                        dialog.cancel()
                    })
                    .show()
            }
        }
    }
}