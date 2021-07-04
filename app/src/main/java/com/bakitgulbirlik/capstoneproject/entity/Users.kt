package com.bakitgulbirlik.capstoneproject.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Users (@SerializedName("mail_adres")
                  @Expose
                  var mail_adres:String,
                  @SerializedName("sifre")
                  @Expose
                  var sifre:String,
                  @SerializedName("ad_soyad")
                  @Expose
                  var ad_soyad:String,
                  @SerializedName("telefon")
                  @Expose
                  var telefon:String,
                  @SerializedName("deger")
                  @Expose
                  var deger:Int ): Serializable {


}