package com.example.mystore.ThanhToanDonHang

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.mystore.R

class DialogInfo : DialogFragment() {
    companion object{
        lateinit var diachi:EditText
        lateinit var phuong:EditText
        lateinit var quan:EditText
        lateinit var thanhpho:EditText
    }
    private lateinit var btnXacNhan:Button
    private lateinit var btnHuy:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.dialoginfo, container, false)
        diachi =view.findViewById(R.id.sonha)
        phuong =view.findViewById(R.id.phuong)
        quan =view.findViewById(R.id.quan)
        thanhpho =view.findViewById(R.id.city)
        btnXacNhan=view.findViewById(R.id.btnXacNhan)
        btnHuy=view.findViewById(R.id.btnHuy)
        btnXacNhan.setOnClickListener {
            if((diachi.text.isEmpty()) ||(phuong.text.isEmpty())||(quan.text.isEmpty())|| (thanhpho.text.isEmpty())){
                val builder=AlertDialog.Builder(context)
                builder.setTitle("THÔNG BÁO")
                    .setMessage("Vui lòng nhập đầy đủ thông tin")
                    .setPositiveButton("OK",DialogInterface.OnClickListener{
                            dialog, id -> dialog.cancel()
                    })
                    .show()
            }
            else{
                ThongTinKhacHang.diachi1.text="Số: "+diachi.text.toString() +", Phường: "+ phuong.text.toString()
                ThongTinKhacHang.diachi2.text="Quận: "+ quan.text.toString() +", Thành phố: "+ thanhpho.text.toString()
                dismiss()
            }
        }
        btnHuy.setOnClickListener {
            dismiss()
        }
        return view
    }
}