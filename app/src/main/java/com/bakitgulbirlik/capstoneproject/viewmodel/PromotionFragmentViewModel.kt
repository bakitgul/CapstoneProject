package com.bakitgulbirlik.capstoneproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bakitgulbirlik.capstoneproject.entity.Products
import com.bakitgulbirlik.capstoneproject.repo.ProductsDaoRepository

class PromotionFragmentViewModel :ViewModel(){
    var promotionList = MutableLiveData<List<Products>>()
    var pdaor = ProductsDaoRepository()

    init {
        loadPromotion()
       promotionList = pdaor.getPromotion()
    }

    fun loadPromotion() {
        pdaor.getAllPromotion()
    }
    fun loadBasket(id:Int, sepet_durum:Int){
        pdaor.addBasket(id,sepet_durum)

    }
}