package com.bakitgulbirlik.capstoneproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bakitgulbirlik.capstoneproject.entity.Products
import com.bakitgulbirlik.capstoneproject.repo.ProductsDaoRepository

class BasketFragmentViewModel :ViewModel(){
    var basketList = MutableLiveData<List<Products>>()
    var pdaor = ProductsDaoRepository()

    init {
        loadBasket()
        basketList = pdaor.getBasket()
    }

    fun loadBasket(){
        pdaor.getAllBasket()
        basketList = pdaor.getBasket()
    }

    fun updateBasket(id:Int,sepet_durum:Int){
        pdaor.addBasket(id,sepet_durum)
    }
}
