package com.bakitgulbirlik.capstoneproject.retrofit

import com.bakitgulbirlik.capstoneproject.entity.CRUDAnswer
import com.bakitgulbirlik.capstoneproject.entity.UsersAnswer
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UsersDaoInterface {

    @POST("uye_ol.php")
    @FormUrlEncoded
    fun userAdd(@Field("mail_adres") mail_adres:String,
                 @Field("sifre") sifre:String,
                 @Field("ad_soyad") ad_soyad:String,
                 @Field("telefon") telefon:Int): Call<CRUDAnswer>


    @POST("giris_yap.php")
    @FormUrlEncoded
    fun usersSelect(@Field("mail_adres") mail_adres: String,
                    @Field("sifre") sifre: String): Call<UsersAnswer>


}

