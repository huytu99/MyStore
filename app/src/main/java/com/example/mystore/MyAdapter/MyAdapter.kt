package com.example.mystore.MyAdapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mystore.MainActivity.DetailItem
import com.example.mystore.MainActivity.TrangChu
import com.example.mystore.R
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.collections.ArrayList

class MyAdapter(private val listPhone:ArrayList<Model>) :RecyclerView.Adapter<MyAdapter.MyViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item=listPhone[position]
        holder.namePhone.text=item.name
        holder.votePhone.text=item.vote
        holder.info1.text=item.detail1
        holder.info2.text=item.detail2
        holder.hangton.text=item.hangton
        val decimalFormat: DecimalFormat = DecimalFormat("###,###,###")
        holder.giaPhone.text=decimalFormat.format(item.gia?.toInt())+"đ"
        Glide.with(holder.imgdetail.context).load(item.imgDetail).into(holder.imgdetail)
        Glide.with(holder.imgPhone.context).load(item.img).into(holder.imgPhone)
        holder.itemView.setOnClickListener {
            val i: Intent = Intent(it.context, DetailItem::class.java)
            i.putExtra("img", item.img)
            i.putExtra("name", item.name)
            i.putExtra("gia", item.gia)
            i.putExtra("info1", item.detail1)
            i.putExtra("info2", item.detail2)
            i.putExtra("imgdetail", item.imgDetail)
            if (TrangChu.list.size > 0) {
                for (i in 0 until TrangChu.list.size) {
                    if (TrangChu.list[i].name == item.name) {
                        item.hangton =
                            (TrangChu.list[i].hangton - TrangChu.list[i].soluong).toString()
                    }
                }
            } else {
                holder.hangton.text = item.hangton
            }
            if(TrangChu.bill2.size > 0){
                for(i in 0 until TrangChu.bill2.size ) {
                    if(TrangChu.bill2[i].name==item.name){
                        item.hangton=(holder.hangton.text.toString().toInt()-TrangChu.bill2[i].soluong.toInt()).toString()
                    }
                }
            }
            else{
                holder.hangton.text = item.hangton
            }
            if (item.hangton.toString().toInt() <= 0) {
                item.hangton = "0"
            }
            i.putExtra("hangton",item.hangton)
            it.context.startActivity(i)
        }
    }
    override fun getItemCount(): Int {
        return listPhone.size
    }
    class MyViewHolder(itemPhone: View) :RecyclerView.ViewHolder(itemPhone){
        val namePhone:TextView=itemPhone.findViewById(R.id.namePhone)
        val votePhone:TextView=itemPhone.findViewById(R.id.votePhone)
        val giaPhone:TextView=itemPhone.findViewById(R.id.giaPhone)
        val imgPhone:ImageView=itemPhone.findViewById(R.id.imgPhone)
        val info1:TextView=itemPhone.findViewById(R.id.info1)
        val info2:TextView=itemPhone.findViewById(R.id.info2)
        val imgdetail:ImageView=itemPhone.findViewById(R.id.imgdetail)
        val hangton:TextView=itemPhone.findViewById(R.id.hangton)

    }

}