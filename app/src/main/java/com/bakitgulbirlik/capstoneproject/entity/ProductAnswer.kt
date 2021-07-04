package com.bakitgulbirlik.capstoneproject.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductAnswer  (@SerializedName("urunler")
                      @Expose
                      var bakitgulproducts:List<Products>,
                      @SerializedName("success")
                      @Expose
                      var success:Int) {
}