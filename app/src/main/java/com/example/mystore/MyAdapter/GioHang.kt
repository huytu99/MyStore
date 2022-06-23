package com.example.mystore.MyAdapter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GioHang (
    var name:String,
    var gia:Long,
    var hangton:Int,
    var img:String,
    var soluong:Int
): Parcelable
