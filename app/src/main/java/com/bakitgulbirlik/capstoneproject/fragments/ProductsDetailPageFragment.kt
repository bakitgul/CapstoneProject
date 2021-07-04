package com.bakitgulbirlik.capstoneproject.fragments

import android.R.attr.bitmap
import android.R.attr.radius
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bakitgulbirlik.capstoneproject.R
import com.bakitgulbirlik.capstoneproject.databinding.FragmentProductsDetailPageBinding
import com.bakitgulbirlik.capstoneproject.entity.CRUDAnswer
import com.bakitgulbirlik.capstoneproject.entity.ProductAnswer
import com.bakitgulbirlik.capstoneproject.retrofit.ApiUtils
import com.bakitgulbirlik.capstoneproject.retrofit.ProductsDaoInterface
import com.bakitgulbirlik.capstoneproject.viewmodel.BasketFragmentViewModel
import com.bakitgulbirlik.capstoneproject.viewmodel.ProductsDetailPageViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_products_detail_page.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductsDetailPageFragment : Fragment() {

    private lateinit var design : FragmentProductsDetailPageBinding
    private lateinit var  viewModel : ProductsDetailPageViewModel
    private lateinit var pdaoi :ProductsDaoInterface



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        design = DataBindingUtil.inflate(inflater,R.layout.fragment_products_detail_page, container, false)
        pdaoi = ApiUtils.getProductsDaoInterface()


        val bundle : ProductsDetailPageFragmentArgs by navArgs()
        var passProduct = bundle.objectProducts

        var product_image_name = bundle.objectProducts.urun_gorsel_url
        var url = "https://docs.google.com/uc?id="+ product_image_name
        var id =design.imageViewDetail
        Picasso.get().load(url).into(id)

        design.productsObject=passProduct

        design.buttonAddToBasket.setOnClickListener {
            viewModel.loadBasket(passProduct.id,1)
            Snackbar.make(it," Add to basket",Snackbar.LENGTH_SHORT).show()

        }



        priceAnimation()

        return design.root
    }

    fun priceAnimation(){
        val anim = ObjectAnimator.ofFloat(textViewDetailPrice,"translationX",-500.0F,0.0f)
                .apply {
                    duration = 3000
                    startDelay = 2000
                    interpolator = BounceInterpolator()
                    repeatCount = ObjectAnimator.INFINITE
                    repeatMode = ObjectAnimator.REVERSE

                }
        anim.start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ProductsDetailPageViewModel by viewModels()
        this.viewModel = tempViewModel
    }


   /* fun getBasket(sepet_durum_degistir: Int){
        pdaoi.getBasket(sepet_durum_degistir).enqueue(object : Callback<ProductAnswer> {
            override fun onResponse(call: Call<ProductAnswer>?, response: Response<ProductAnswer>?) {
                val basketList = response?.body()?.bakitgulproducts
            }

            override fun onFailure(call: Call<ProductAnswer>?, t: Throwable?) {}
        })
    }*/

}