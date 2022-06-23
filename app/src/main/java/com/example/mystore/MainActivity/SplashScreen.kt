package com.example.mystore.MainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.mystore.Admin.Login
import com.example.mystore.R

class SplashScreen : AppCompatActivity() {
    private var time: Long=2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)
        val actionBar=supportActionBar
        actionBar?.hide()
        Handler().postDelayed(Runnable {
//            val i= Intent(this,TrangChu::class.java)
            val i= Intent(this,Login::class.java)
            startActivity(i)
            finish()
        },time)

    }
}