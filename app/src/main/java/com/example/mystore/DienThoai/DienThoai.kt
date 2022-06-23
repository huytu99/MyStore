package com.example.mystore.DienThoai

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.mystore.R
import kotlinx.android.synthetic.main.dienthoai.*

class DienThoai : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val actionBar=supportActionBar
        actionBar?.setTitle("Điện Thoại")
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FE724C")))
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dienthoai)
        if (savedInstanceState==null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<BestPhone>(R.id.frame)
            }
        }
        samsung.setOnClickListener {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<SamSung>(R.id.frame)
                }
            }
            vmart.setOnClickListener {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<VsMart>(R.id.frame)
                }
            }
            realme.setOnClickListener {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<RealMe>(R.id.frame)
                }
            }
            oppo.setOnClickListener {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<Oppo>(R.id.frame)
                }
            }
            iphone.setOnClickListener {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<iPhone>(R.id.frame)
                }
            }
    }
    @Override
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}