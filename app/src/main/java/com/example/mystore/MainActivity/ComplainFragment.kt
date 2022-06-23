package com.example.mystore.MainActivity

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.mystore.MyAdapter.ModelComplain
import com.example.mystore.R
class ComplainFragment : DialogFragment() {
    lateinit var txtphanhoi:EditText
    lateinit var btnphanhoi:Button
    lateinit var btnhuyphanhoi:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_complain, container, false)
        txtphanhoi=view.findViewById(R.id.txtphanhoi)
        btnphanhoi=view.findViewById(R.id.phanhoi)
        btnhuyphanhoi=view.findViewById(R.id.huyphanhoi)
        btnphanhoi.setOnClickListener {
            if(txtphanhoi.text.isEmpty()){
                val builder= AlertDialog.Builder(context)
                builder.setTitle("Thông Báo")
                    .setMessage("Chưa có phản hồi")
                    .setPositiveButton("OK", DialogInterface.OnClickListener{
                            dialog, which -> dialog.cancel()
                    })
                    .show()
            }
            else {
                TrangChu.listComplain.add(ModelComplain(DetailItem.tenSP.text.toString(),
                    txtphanhoi.text.toString()))
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Thông Báo")
                    .setMessage("Cảm ơn những góp ý của bạn")
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                        dismiss()
                    })
                    .show()
            }
        }
        btnhuyphanhoi.setOnClickListener {
            dismiss()
        }
        return view
    }
}