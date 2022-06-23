package com.example.mystore.MyAdapter
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mystore.MainActivity.TrangChu
import com.example.mystore.R
import com.example.mystore.ShoppingCart.Cart
import java.text.DecimalFormat

class AdapterGioHang(private val arrayList:ArrayList<GioHang>) : RecyclerView.Adapter<AdapterGioHang.MyViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterGioHang.MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.giohang,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item=arrayList[position]
        holder.nameCart.text=item.name
        val decimalFormat: DecimalFormat = DecimalFormat("###,###,###")
        holder.giaCart.text=decimalFormat.format(item.gia)+"đ"
        holder.soluong.text=item.soluong.toString()
        holder.hangton.text=item.hangton.toString()
        Glide.with(holder.imgCart).load(item.img).into(holder.imgCart)
        holder.delete.setOnClickListener {
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle("THÔNG BÁO")
            builder.setMessage("Sản phẩm sẽ bị xóa khỏi giỏ hàng."+"\n"+"Bạn chắc chứ?")
                .setNegativeButton("YES",DialogInterface.OnClickListener{
                    dialog, id -> run {
                    TrangChu.list.remove(item)
                    notifyDataSetChanged()
                    var TongTien:Long=0
                    for(i in 0 until TrangChu.list.size){
                        TongTien+=TrangChu.list[i].gia
                    }
                    Cart.tongdonhang.text=decimalFormat.format(TongTien)+"đ"
                    if(TrangChu.list.size<=0){
                        Cart.adapterGioHang.notifyDataSetChanged()
                        Cart.recyclerView.isVisible = false
                        Cart.txtthongbao.isVisible = true
                    }
                }
                })
                .setPositiveButton("NO",DialogInterface.OnClickListener{
                    dialog, id -> dialog.cancel()
                })
                .show()
        }
        if(holder.soluong.text.toString().toInt()<=1){
            holder.tru.isVisible=false
        }
        if(holder.soluong.text.toString().toInt()>=holder.hangton.text.toString().toInt()){
            holder.cong.isVisible=false
        }
        holder.cong.setOnClickListener {
            val soNew :Int= holder.soluong.text.toString().toInt() + 1
            val soOld:Int=TrangChu.list[position].soluong
            val giaOld:Long=TrangChu.list[position].gia
            TrangChu.list[position].soluong=soNew
            val giaNew:Long= ((giaOld*soNew)/soOld)
            TrangChu.list[position].gia=giaNew
            val decimalFormat: DecimalFormat = DecimalFormat("###,###,###")
            holder.giaCart.text=decimalFormat.format(giaNew)+"đ"
            holder.soluong.text=soNew.toString()
            var TongTien:Long=0
            for(i in 0 until TrangChu.list.size){
                TongTien+=TrangChu.list[i].gia
            }
            Cart.tongdonhang.text=decimalFormat.format(TongTien)+"đ"
            if(holder.soluong.text.toString().toInt()>=2){
                holder.tru.isVisible=true
            }

            if(holder.soluong.text.toString().toInt()>(holder.hangton.text.toString().toInt()-1)){
                val builder = AlertDialog.Builder(holder.itemView.context)
                builder.setTitle("THÔNG BÁO")
                builder.setMessage("Số hàng tồn không đủ.")
                    .setPositiveButton("OK",DialogInterface.OnClickListener{
                        dialog, id -> dialog.cancel()
                    })
                    .show()
                holder.cong.isVisible=false
            }
        }
        holder.tru.setOnClickListener {
            val soNew :Int= holder.soluong.text.toString().toInt() - 1
            val soOld:Int=TrangChu.list[position].soluong
            val giaOld:Long=TrangChu.list[position].gia
            TrangChu.list[position].soluong=soNew
            val giaNew:Long= ((giaOld*soNew)/soOld)
            TrangChu.list[position].gia=giaNew
            val decimalFormat: DecimalFormat = DecimalFormat("###,###,###")
            holder.giaCart.text=decimalFormat.format(giaNew)+"đ"
            holder.soluong.text=soNew.toString()
            var TongTien:Long=0
            for(i in 0 until TrangChu.list.size){
                TongTien+=TrangChu.list[i].gia
            }
            Cart.tongdonhang.text=decimalFormat.format(TongTien)+"đ"
            if(holder.soluong.text.toString().toInt()<=1){
                holder.tru.isVisible=false
            }
            if(holder.soluong.text.toString().toInt()<=(holder.hangton.text.toString().toInt()-1)){
                holder.cong.isVisible=true
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val nameCart:TextView=view.findViewById(R.id.nameCart)
        val imgCart:ImageView=view.findViewById(R.id.imgCart)
        val giaCart:TextView=view.findViewById(R.id.giaCart)
        val soluong:TextView=view.findViewById(R.id.soluongCart)
        val hangton:TextView=view.findViewById(R.id.hangton)
        val delete:Button=view.findViewById(R.id.deleteCart)
        val cong:Button=view.findViewById(R.id.congCart)
        val tru:Button=view.findViewById(R.id.truCart)
    }

}