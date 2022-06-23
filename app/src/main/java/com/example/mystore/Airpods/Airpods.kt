package com.example.mystore.Airpods

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystore.MyAdapter.Model
import com.example.mystore.MyAdapter.MyAdapter
import com.example.mystore.R
import com.google.firebase.database.*

class Airpods : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    lateinit var recyclerView: RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var listPhone:ArrayList<Model>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainitem)
        val actionBar=supportActionBar
        actionBar?.setTitle("AirPods")
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FE724C")))
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        linearLayoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        recyclerView=findViewById(R.id.rcviewairpods)
        recyclerView.layoutManager=linearLayoutManager
        recyclerView.setHasFixedSize(true)
        listPhone= arrayListOf<Model>()
        getlistPhone()
    }
    private fun getlistPhone(){
        databaseReference= FirebaseDatabase.getInstance().getReference("AirPods")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(phoneSnapshot in snapshot.children){
                        val phoneList=phoneSnapshot.getValue(Model::class.java)
                        listPhone.add(phoneList!!)
                    }
                    var adapter= MyAdapter(listPhone)
                    recyclerView.adapter=adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    @Override
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}