package com.example.mystore.MyAdapter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelBill(
    var hoten:String,
    var phone:String,
    var diachi1:String,
    var diachi2:String,
    var maso:Int,
    var hinhthuc:String,
    var uudai:String,
    var phiship:String,
    var tongtien:String):Parcelable


