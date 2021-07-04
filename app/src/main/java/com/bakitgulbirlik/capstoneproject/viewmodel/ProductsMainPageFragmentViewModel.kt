package com.bakitgulbirlik.capstoneproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bakitgulbirlik.capstoneproject.entity.Products
import com.bakitgulbirlik.capstoneproject.repo.ProductsDaoRepository
import com.bakitgulbirlik.capstoneproject.retrofit.ProductsDaoInterface

class ProductsMainPageFragmentViewModel:ViewModel() {
    var productsList = MutableLiveData<List<Products>>()
    private val pdaor = ProductsDaoRepository()

    init {
        loadProducts()
        productsList = pdaor.getProducts()
    }

    fun loadProducts() {
        pdaor.getAllProducts()
    }

    fun loadBasket(id:Int, sepet_durum:Int){
        pdaor.addBasket(id,sepet_durum)

    }


}