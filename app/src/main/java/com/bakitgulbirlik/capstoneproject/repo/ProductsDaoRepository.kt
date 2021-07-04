package com.bakitgulbirlik.capstoneproject.repo

import androidx.lifecycle.MutableLiveData
import com.bakitgulbirlik.capstoneproject.entity.CRUDAnswer
import com.bakitgulbirlik.capstoneproject.entity.ProductAnswer
import com.bakitgulbirlik.capstoneproject.entity.Products
import com.bakitgulbirlik.capstoneproject.retrofit.ApiUtils
import com.bakitgulbirlik.capstoneproject.retrofit.ProductsDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsDaoRepository {
    private val pdaoi : ProductsDaoInterface
    private val productsList: MutableLiveData<List<Products>>
    private val promotionList: MutableLiveData<List<Products>>
    private val basketList: MutableLiveData<List<Products>>

    init {
        pdaoi = ApiUtils.getProductsDaoInterface()
        productsList = MutableLiveData()
        promotionList = MutableLiveData()
        basketList = MutableLiveData()

    }
    fun getAllProducts(){
        pdaoi.searchProducts("bakitgulbirlik").enqueue(object : Callback<ProductAnswer>{
            override fun onResponse(call: Call<ProductAnswer>?, response: Response<ProductAnswer>?) {
                val product = response?.body()?.bakitgulproducts
                productsList.value = product

            }
            override fun onFailure(call: Call<ProductAnswer>?, t: Throwable?) {}
        })
    }

    fun getProducts(): MutableLiveData<List<Products>>{
        return productsList
    }

    fun getAllPromotion(){
        pdaoi.searchProducts("bakitgulbirlik").enqueue(object : Callback<ProductAnswer>{
            override fun onResponse(call: Call<ProductAnswer>?, response: Response<ProductAnswer>?) {
                val list = response!!.body()!!.bakitgulproducts
                val tempList = ArrayList<Products>()
                productsList.value = list
                for (k in 0 until list.size){
                    if(list[k].urun_indirimli_mi == 1){
                        tempList.add(list[k])
                    }
                    promotionList.value = tempList

                }

            }
            override fun onFailure(call: Call<ProductAnswer>?, t: Throwable?) {}
        })
    }

    fun getPromotion(): MutableLiveData<List<Products>>{
        return promotionList
    }

    fun getAllBasket(){
        pdaoi.searchProducts("bakitgulbirlik").enqueue(object : Callback<ProductAnswer>{
            override fun onResponse(call: Call<ProductAnswer>?, response: Response<ProductAnswer>?) {
                val list = response!!.body()!!.bakitgulproducts
                val tempList = ArrayList<Products>()
                //basketList.value = list
                for(k in 0 until list.size){
                    if(list[k].sepet_durum ==1){
                        tempList.add(list[k])
                    }
                    basketList.value =tempList
                }
            }

            override fun onFailure(call: Call<ProductAnswer>?, t: Throwable?) {}
        })
    }

    fun getBasket(): MutableLiveData<List<Products>>{
        return basketList
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

    }


