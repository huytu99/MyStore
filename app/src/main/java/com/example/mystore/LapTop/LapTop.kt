package com.example.mystore.LapTop

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.mystore.R
import kotlinx.android.synthetic.main.laptop.*

class LapTop : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.laptop)
        val actionBar=supportActionBar
        actionBar?.setTitle("LapTop")
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FE724C")))
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        if(savedInstanceState==null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<BestLaptop>(R.id.frame)
            }
        }
        asus.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<Asus>(R.id.frame)
            }
        }
        dell.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<Dell>(R.id.frame)
            }
        }
        hp.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<HP>(R.id.frame)
            }
        }
        lenovo.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<Lenovo>(R.id.frame)
            }
        }
        macbook.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<Macbook>(R.id.frame)
            }
        }
        msi.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<MSI>(R.id.frame)
            }
        }
    }
    @Override
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}