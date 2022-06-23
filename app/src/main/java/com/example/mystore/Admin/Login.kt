package com.example.mystore.Admin

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.mystore.MainActivity.TrangChu
import com.example.mystore.R

class Login : AppCompatActivity() {
    lateinit var edtAccount:EditText
    lateinit var edtPassword:EditText
    lateinit var txtsignup:TextView
    lateinit var btnsignin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var actionBar=supportActionBar
        actionBar!!.hide()
        txtsignup=findViewById(R.id.txtsignup)
        edtAccount=findViewById(R.id.edtaccount)
        edtPassword=findViewById(R.id.edtpassword)
        btnsignin=findViewById(R.id.btnsignin)
        txtsignup.setOnClickListener {
            val dialogsignup=SignUpFM()
            dialogsignup.show(supportFragmentManager,"BBB")
        }
        btnsignin.setOnClickListener {
            if((edtAccount.text.toString()==AccountAdmin().tkAdmin) && (edtPassword.text.toString()==AccountAdmin().mkAdmin)){
                edtAccount.text.clear()
                edtPassword.text.clear()
                startActivity(Intent(this,VertifiPhone::class.java))
            }
            else if ((edtAccount.text.isEmpty()) || (edtPassword.text.isEmpty())){
                val builder= AlertDialog.Builder(this)
                builder.setTitle("Thông Báo")
                    .setMessage("Vui lòng nhập đầy đủ thông tin")
                    .setPositiveButton("OK", DialogInterface.OnClickListener{
                            dialog, which -> dialog.cancel()
                    })
                    .show()
            }
            else {
                for (user in SignUpFM.listAccount) {
                    if ((edtAccount.text.toString() == user.taikhoan) && (edtPassword.text.toString() == user.matkhau)) {
                        edtAccount.text.clear()
                        edtPassword.text.clear()
                        startActivity(Intent(this, TrangChu::class.java))
                    }
                }
            }
        }
    }
}