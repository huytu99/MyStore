package com.example.mystore.ShoppingCart
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystore.Admin.StoreManager
import com.example.mystore.MainActivity.TrangChu
import com.example.mystore.MyAdapter.AdapterGioHang
import com.example.mystore.R
import com.example.mystore.ThanhToanDonHang.ThongTinKhacHang
import java.text.DecimalFormat

class Cart : Fragment() {
    companion object{
        lateinit var tongdonhang: TextView
        lateinit var txtthongbao: TextView
        lateinit var recyclerView: RecyclerView
        lateinit var adapterGioHang: AdapterGioHang
    }
    private lateinit var btnTieptuc: Button
    private lateinit var btnThanhtoan: Button
    lateinit var btnReload: Button

    private var viewManager = LinearLayoutManager(context)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.shoppingcart, container, false)
        //Set up ActionBar
        (activity as AppCompatActivity).supportActionBar?.title = " Giỏ Hàng Của Bạn"
        (activity as AppCompatActivity).supportActionBar?.setLogo(R.drawable.icongiohang)
        //Đổ Adapter cho giỏ hàng
        recyclerView = view.findViewById(R.id.rcgiohang)
        txtthongbao = view.findViewById(R.id.txtgiohang)
        btnThanhtoan = view.findViewById(R.id.thanhtoan)
        btnTieptuc = view.findViewById(R.id.tieptuc)
        btnReload = view.findViewById(R.id.reload)
        tongdonhang = view.findViewById(R.id.tongdonhang)
        recyclerView.layoutManager = viewManager
        recyclerView.setHasFixedSize(true)
        adapterGioHang = AdapterGioHang(TrangChu.list)
        recyclerView.adapter = adapterGioHang
        //Kiểm tra dữ liệu
        if (TrangChu.list.size <= 0) {
            adapterGioHang.notifyDataSetChanged()
            recyclerView.isVisible = false
            txtthongbao.isVisible = true
        } else {
            adapterGioHang.notifyDataSetChanged()
            recyclerView.isVisible = true
            txtthongbao.isVisible = false
        }
        btnTieptuc.setOnClickListener {
            startActivity(Intent(context, TrangChu::class.java))
        }
        btnThanhtoan.setOnClickListener {
            if(TrangChu.list.size<=0){
                val builder = AlertDialog.Builder(context)
                builder.setTitle("THÔNG BÁO")
                builder.setMessage("Giỏ hàng của bạn trống."+"\n"+"Vui lòng kiểm tra lại"+"\n"+"Xin cảm ơn")
                    .setPositiveButton("OK", DialogInterface.OnClickListener{
                            dialog, id -> startActivity(Intent(context,TrangChu::class.java))
                    })
                    .show()
            }
            else{
                startActivity(Intent(context,ThongTinKhacHang::class.java))
            }
        }
        TongDonHang()
        return view
    }
    fun TongDonHang(){
        var TongTien: Long = 0
        for (i in 0 until TrangChu.list.size) {
            TongTien += TrangChu.list[i].gia
        }
        val decimalFormat: DecimalFormat = DecimalFormat("###,###,###")
        tongdonhang.text= decimalFormat.format(TongTien) + "đ"
    }
}