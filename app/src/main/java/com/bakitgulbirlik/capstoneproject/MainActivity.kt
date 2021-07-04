package com.bakitgulbirlik.capstoneproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.bakitgulbirlik.capstoneproject.entity.CRUDAnswer
import com.bakitgulbirlik.capstoneproject.entity.ProductAnswer
import com.bakitgulbirlik.capstoneproject.entity.Products
import com.bakitgulbirlik.capstoneproject.entity.UsersAnswer
import com.bakitgulbirlik.capstoneproject.retrofit.ApiUtils
import com.bakitgulbirlik.capstoneproject.retrofit.ProductsDaoInterface
import com.bakitgulbirlik.capstoneproject.retrofit.UsersDaoInterface
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_log_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var pdaoi: ProductsDaoInterface

    val basket_array = arrayListOf<Products>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pdaoi = ApiUtils.getProductsDaoInterface()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNav, navHostFragment.navController)


        //dongu()
        //donguKampanya()
        //dongupromotion()
        getProduct("bakitgulbirlik")
        addPromotion(635,1)
        addPromotion(636,1)
        addPromotion(637,1)

        //getPromotion()
        getArray()
    }

    fun getArray():ArrayList<Products> = basket_array

    fun dongu () {
        val urun_adi = arrayOf("Miyuki Bracelet","Miyuki Bracelet","Miyuki Bracelet",
                "Miyuki Necklace","Miyuki Necklace","Miyuki Necklace",
                "Miyuki Pin","Miyuki Pin","Miyuki Pin","Miyuki Pin","Miyuki Pin","Miyuki Pin",
                "Miyuki Hair Pin","Miyuki Hair Pin","Miyuki Hair Pin")
        val urun_fiyat = arrayOf("15.99","15.99","15.99",
                "25.99","25.99","25.99",
                "15.99","15.99","15.99","15.99","15.99","15.99",
                "35.99","35.99","35.99")
        val urun_aciklama = arrayOf("Handmade Bracelet from Miyuki beads.Warning!! Avoid alcohol such as perfume and cologne.",
                "Handmade Bracelet from Miyuki beads.Warning!! Avoid alcohol such as perfume and cologne.",
                "Handmade Bracelet from Miyuki beads.Warning!! Avoid alcohol such as perfume and cologne.",
                "Handmade Neclace from Miyuki beads.Warning!! Avoid alcohol such as perfume and cologne.",
                "Handmade Neclace from Miyuki beads.Warning!! Avoid alcohol such as perfume and cologne.",
                "Handmade Neclace from Miyuki beads.Warning!! Avoid alcohol such as perfume and cologne.",
                "Handmade Pin from Miyuki beads.Warning!! Avoid alcohol such as perfume and cologne.",
                "Handmade Pin from Miyuki beads.Warning!! Avoid alcohol such as perfume and cologne.",
                "Handmade Pin from Miyuki beads.Warning!! Avoid alcohol such as perfume and cologne.",
                "Handmade Pin from Miyuki beads.Warning!! Avoid alcohol such as perfume and cologne.",
                "Handmade Pin from Miyuki beads.Warning!! Avoid alcohol such as perfume and cologne.",
                "Handmade Pin from Miyuki beads.Warning!! Avoid alcohol such as perfume and cologne.",
                "Handmade Hair Pin from Miyuki beads.Warning!! Avoid alcohol such as perfume and cologne.",
                "Handmade Hair Pin from Miyuki beads.Warning!! Avoid alcohol such as perfume and cologne.",
                "Handmade Hair Pin from Miyuki beads.Warning!! Avoid alcohol such as perfume and cologne.")
        val urun_gorsel_url:Array<String> = arrayOf("1wswqbDGsqFlxVgNZQUd-_WWFuAXClksZ","1M4Y0fNybs99PFMd841bXcxeKQd0qb7wm","1cPWVPSqsIDGvUhXwj7e1aeYmG1gYO2cF",
                "1_cG7BvvhjsDf9CaSAp-bN7uOm-bRqexL","122eiEpDW4KTU1CDWpxdayKDgsPmgSsaZ","1LsGcszjVZRUEqQC9md1vCqczf1-ybQB1",
                "1B2HDKeU8QySeGMGWKnEBmAixN_7R1Yzm","1jAQya14FJdhVOVRySyV-X6g5vbERgNA7","1bREv4w6mtNoMhW6NT4x6PJTwZX9Djg5e","1lrjVfhBB_WWW-ruhcupxeNkIYJsQD--7","1cOiiBcNt6oHWQyK4GeCBlQpP-KvH4vcg","1WTlJZTYgkm9RrZEDEmhwO_Y9y1fk-xOu",
                "1L2yLL6uKnXdtOVsV1sIjCW2d43EnM8tT","1UGVDX8v_jOt-bZOqw2WqPbi1XV1JzcZX","1qyQruPTJWhZBDTc9zZxtbM4eMBCzQOuK")



        for(k in 0 until urun_adi.size){
            addProduct("bakitgulbirlik",urun_adi[k],urun_fiyat[k],urun_aciklama[k],urun_gorsel_url[k])
        }
    }

    fun donguKampanya(){
        val urun_adi:Array<String> = arrayOf("Miyuki Pins", "Miyuki Pins", "Miyuki Hair Pin Set")
        val urun_fiyat:Array<String> = arrayOf("32.99","28.99", "121.99")
        val urun_aciklama:Array<String> = arrayOf("Handmade Pin from Miyuki beads.Warning!! Avoid alcohol such as perfume and cologne.",
        "Handmade Pin from Miyuki beads.Warning!! Avoid alcohol such as perfume and cologne.",
        "Handmade Hair Pin from Miyuki beads.Warning!! Avoid alcohol such as perfume and cologne.")
        val urun_gorsel_url:Array<String> = arrayOf("1DO5DXTb-iyq0nzfoNv2QDhEgC1rN-iWL","1hDFY09Mw7BdHW9yyOrJvyUPY-XL2uUO-","1uzyvkb_WXuSE8sj-gBM_pJlvErTjIBc5")

        for(i in 0 until urun_adi.size){
            addProduct("bakitgulbirlik",urun_adi[i],urun_fiyat[i],urun_aciklama[i],urun_gorsel_url[i])
        }
    }

    fun addProduct(satici_adi:String,urun_adi:String,urun_fiyat:String,urun_aciklama:String,urun_gorsel_url:String){
        pdaoi.productAdd(satici_adi,urun_adi,urun_fiyat,urun_aciklama,urun_gorsel_url)
                        .enqueue(object :Callback<CRUDAnswer>{
            override fun onResponse(call: Call<CRUDAnswer>?, response: Response<CRUDAnswer>) {
                println(response.body().success.toString())
                println(response.body().message)
            }

            override fun onFailure(call: Call<CRUDAnswer>?, t: Throwable?) {
                println(t?.localizedMessage.toString())
            }
                        })
    }

    fun getProduct(satici_adi: String){
        pdaoi.searchProducts(satici_adi).enqueue(object : Callback<ProductAnswer> {
            override fun onResponse(call: Call<ProductAnswer>?, response: Response<ProductAnswer>) {
                val productsList = response!!.body()!!.bakitgulproducts

                for(k in productsList){
                    Log.e("*******","*****")
                    Log.e("Id",k.id.toString())
                    Log.e("Product Name", k.urun_adi)
                    Log.e("Sepet Durum", k.sepet_durum.toString())

                }
            }

            override fun onFailure(call: Call<ProductAnswer>?, t: Throwable?) {}
        })
    }

    fun addPromotion(id:Int,urun_indirimli_mi:Int){
        pdaoi.promotionProduct(id,urun_indirimli_mi).enqueue(object :Callback<CRUDAnswer>{
            override fun onResponse(call: Call<CRUDAnswer>?, response: Response<CRUDAnswer>?) {

                println(response?.body()?.success.toString())
                println(response?.body()?.message)
            }

            override fun onFailure(call: Call<CRUDAnswer>?, t: Throwable?) {}
        })
    }

    fun dongupromotion(){
        val id:Array<Int> = arrayOf(16,17,18)
        for(i in 0 until id.size){
            addPromotion(id[i],1)
        }
    }

    fun getPromotion(id: Int){
        pdaoi.getpromotionproduct(id).enqueue(object :Callback<ProductAnswer>{
            override fun onResponse(call: Call<ProductAnswer>?, response: Response<ProductAnswer>?) {
                val promotionList =response?.body()?.bakitgulproducts
            }

            override fun onFailure(call: Call<ProductAnswer>?, t: Throwable?) {}
        })
    }

    fun addBasket(id: Int,sepet_durum_degistir:Int){
        pdaoi.basketUpdate(id,sepet_durum_degistir).enqueue(object :Callback<CRUDAnswer>{
            override fun onResponse(call: Call<CRUDAnswer>?, response: Response<CRUDAnswer>?) {
                println(response?.body()?.success.toString())
                println(response?.body()?.message)
            }

            override fun onFailure(call: Call<CRUDAnswer>?, t: Throwable?) {}
        })
    }

   /*fun getBasket(id: Int,sepet_durum_degistir: Int){
        pdaoi.basketUpdate(id,1).enqueue(object : Callback<ProductAnswer>{
            override fun onResponse(call: Call<ProductAnswer>?, response: Response<ProductAnswer>?) {
                val basketList = response?.body()?.bakitgulproducts
            }

            override fun onFailure(call: Call<ProductAnswer>?, t: Throwable?) {}
        })
    }*/







}