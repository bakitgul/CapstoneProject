package com.bakitgulbirlik.capstoneproject.retrofit

import com.bakitgulbirlik.capstoneproject.entity.CRUDAnswer
import com.bakitgulbirlik.capstoneproject.entity.ProductAnswer
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductsDaoInterface {

    @POST("urunler.php")
    @FormUrlEncoded
    fun searchProducts(@Field("satici_adi") satici_adi: String) :Call<ProductAnswer>

    @POST("urun_ekle.php")
    @FormUrlEncoded
    fun productAdd(@Field("satici_adi") satici_adi:String,
                @Field("urun_adi") urun_adi:String,
                @Field("urun_fiyat") urun_fiyat:String,
                @Field("urun_aciklama") urun_aciklama:String,
                @Field("urun_gorsel_url") urun_gorsel_url:String): Call<CRUDAnswer>

    @POST("sepet_durum_degistir.php")
    @FormUrlEncoded
    fun basketUpdate(@Field("id") id:Int,
                     @Field("sepet_durum") sepet_durum:Int): Call<CRUDAnswer>



    @POST("indirimli_urun_durum_degistir.php")
    @FormUrlEncoded
    fun promotionProduct (@Field("id") id: Int,
                          @Field("urun_indirimli_mi") urun_indirimli_mi:Int): Call<CRUDAnswer>

    @GET("urunler.php")
    @FormUrlEncoded
    fun getpromotionproduct(@Field("id") id:Int): Call<ProductAnswer>

}


