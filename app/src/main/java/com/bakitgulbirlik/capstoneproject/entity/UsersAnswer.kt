package com.bakitgulbirlik.capstoneproject.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UsersAnswer (@SerializedName("kullanicilar")
                        @Expose
                        var bakitgulusers:List<Users>,
                        @SerializedName("success")
                        @Expose
                        var success:Int){
}