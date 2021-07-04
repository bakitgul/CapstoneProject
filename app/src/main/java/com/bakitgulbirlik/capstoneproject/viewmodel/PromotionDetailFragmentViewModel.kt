package com.bakitgulbirlik.capstoneproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bakitgulbirlik.capstoneproject.entity.Products
import com.bakitgulbirlik.capstoneproject.repo.ProductsDaoRepository

class PromotionDetailFragmentViewModel: ViewModel() {
    var productsList = MutableLiveData<List<Products>>()
    private val pdaor = ProductsDaoRepository()

    init {

        productsList = pdaor.getProducts()
    }

    fun loadBasket(id:Int, sepet_durum:Int){
        pdaor.addBasket(id,sepet_durum)

    }

}