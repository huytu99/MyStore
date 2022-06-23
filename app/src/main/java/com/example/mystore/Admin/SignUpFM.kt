package com.example.mystore.Admin

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.mystore.R
class SignUpFM : DialogFragment() {
    companion object{
        var listAccount=ArrayList<AccountUser>()

    }
    lateinit var edttk:EditText
    lateinit var edtemail:EditText
    lateinit var edtmk:EditText
    lateinit var btndangki:Button
    lateinit var btnhuydangki:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_sign_up, container, false)
        edttk=view.findViewById(R.id.edttk)
        edtmk=view.findViewById(R.id.edtmk)
        edtemail=view.findViewById(R.id.edtemail)
        btndangki=view.findViewById(R.id.dangki)
        btnhuydangki=view.findViewById(R.id.huydangki)
        btndangki.setOnClickListener {
            if((edttk.text.isEmpty())||(edtmk.text.isEmpty())||(edtemail.text.isEmpty())){
                val builder=AlertDialog.Builder(context)
                builder.setTitle("Thông Báo")
                    .setMessage("Vui lòng nhập đầy đủ thông tin")
                    .setPositiveButton("OK",DialogInterface.OnClickListener{
                            dialog, which -> dialog.cancel()
                    })
                    .show()
            }
            else if (edttk.text.toString()==AccountAdmin().tkAdmin){
                val builder=AlertDialog.Builder(context)
                builder.setTitle("Thông Báo")
                    .setMessage("Tài khoản đã tồn tại")
                    .setPositiveButton("OK",DialogInterface.OnClickListener{
                            dialog, which -> dialog.cancel()
                    })
                    .show()
            }
            else if (edttk.text.toString()==edtmk.text.toString()){
                val builder=AlertDialog.Builder(context)
                builder.setTitle("Thông Báo")
                    .setMessage("Tài khoản và mật khẩu không được trùng nhau")
                    .setPositiveButton("OK",DialogInterface.OnClickListener{
                            dialog, which -> dialog.cancel()
                    })
                    .show()
            }
            else if (listAccount.size<=0){
                val builder=AlertDialog.Builder(context)
                builder.setTitle("Thông Báo")
                    .setMessage("Đăng kí thành công")
                    .setPositiveButton("OK",DialogInterface.OnClickListener{
                            dialog, which -> dialog.cancel()
                    })
                    .show()
                listAccount.add(AccountUser(edttk.text.toString(),edtmk.text.toString(),edtemail.text.toString()))
                dismiss()
            }
            else{
                for( user in listAccount){
                    if(user.taikhoan==edttk.text.toString()){
                        val builder=AlertDialog.Builder(context)
                        builder.setTitle("Thông Báo")
                            .setMessage("Tài khoản đã tồn tại")
                            .setPositiveButton("OK",DialogInterface.OnClickListener{
                                    dialog, which -> dialog.cancel()
                            })
                            .show()
                    }
                    else{
                        val builder=AlertDialog.Builder(context)
                        builder.setTitle("Thông Báo")
                            .setMessage("Đăng kí thành công")
                            .setPositiveButton("OK",DialogInterface.OnClickListener{
                                    dialog, which -> dialog.cancel()
                            })
                            .show()
                        listAccount.add(AccountUser(edttk.text.toString(),edtmk.text.toString(),edtemail.text.toString()))
                        dismiss()
                    }
                }
            }
        }
        btnhuydangki.setOnClickListener {
            dismiss()
        }
        Log.d("EEE", listAccount.toString())
        return view
    }
}