package com.bakitgulbirlik.capstoneproject.fragments

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bakitgulbirlik.capstoneproject.R
import com.bakitgulbirlik.capstoneproject.databinding.FragmentPromotionBinding
import com.bakitgulbirlik.capstoneproject.databinding.FragmentPromotionDetailBinding
import com.bakitgulbirlik.capstoneproject.retrofit.ApiUtils
import com.bakitgulbirlik.capstoneproject.retrofit.ProductsDaoInterface
import com.bakitgulbirlik.capstoneproject.viewmodel.BasketFragmentViewModel
import com.bakitgulbirlik.capstoneproject.viewmodel.PromotionDetailFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class PromotionDetailFragment : Fragment() {
    private lateinit var design: FragmentPromotionDetailBinding
    private lateinit var pdaoi : ProductsDaoInterface
    private lateinit var viewModel : PromotionDetailFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        design = DataBindingUtil.inflate(inflater,R.layout.fragment_promotion_detail, container, false)
        pdaoi = ApiUtils.getProductsDaoInterface()


        val bundle : PromotionDetailFragmentArgs by navArgs()
        var passPromotion = bundle.objPromotion

        var discount = listOf<Double>(79.88,25.99,28.99)
        design.textViewPromotionDetailPrice.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }

        design.textViewDetailPrice2.text = "79.88 ₺"



        var product_image_name = bundle.objPromotion.urun_gorsel_url
        var url = "https://docs.google.com/uc?id="+ product_image_name
        var id = design.imageViewPromotionDetail
        Picasso.get().load(url).into(id)

        design.proObject=passPromotion

        design.buttonAddToBasket2.setOnClickListener {
            viewModel.loadBasket(passPromotion.id,1)
            Snackbar.make(it," Add to basket",Snackbar.LENGTH_SHORT).show()
        }


        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: PromotionDetailFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }

}