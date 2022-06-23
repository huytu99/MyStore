package com.example.mystore.HotSale

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystore.MyAdapter.Model
import com.example.mystore.MyAdapter.MyAdapter
import com.example.mystore.R
import com.google.firebase.database.*

class HotSale : Fragment() {
    private lateinit var databaseReference: DatabaseReference
    lateinit var recyclerView: RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var listPhone:ArrayList<Model>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.title=" Sản phẩm được yêu thích"
        (activity as AppCompatActivity).supportActionBar?.setLogo(R.drawable.iconhot)
        val view= inflater.inflate(R.layout.activity_mainitem, container, false)
        linearLayoutManager= LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerView=view.findViewById(R.id.rcviewairpods)
        recyclerView.layoutManager=linearLayoutManager
        recyclerView.setHasFixedSize(true)
        listPhone= arrayListOf<Model>()
        getlistPhone()
        return view
    }
    private fun getlistPhone(){
        databaseReference= FirebaseDatabase.getInstance().getReference("BestSeller")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(phoneSnapshot in snapshot.children){
                        val phoneList=phoneSnapshot.getValue(Model::class.java)
                        listPhone.add(phoneList!!)
                    }
                    val adapter= MyAdapter(listPhone)
                    recyclerView.adapter=adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}