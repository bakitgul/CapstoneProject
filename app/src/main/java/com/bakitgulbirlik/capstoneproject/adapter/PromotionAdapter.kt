package com.bakitgulbirlik.capstoneproject.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bakitgulbirlik.capstoneproject.databinding.CardDesignBinding
import com.bakitgulbirlik.capstoneproject.databinding.PromotionCarddesignBinding
import com.bakitgulbirlik.capstoneproject.entity.Products
import com.bakitgulbirlik.capstoneproject.fragments.ProductsMainPageFragmentDirections
import com.bakitgulbirlik.capstoneproject.fragments.PromotionFragmentDirections
import com.bakitgulbirlik.capstoneproject.viewmodel.PromotionFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class PromotionAdapter (var mContext: Context, var promotionList:List<Products>, var viewModel: PromotionFragmentViewModel)
    : RecyclerView.Adapter<PromotionAdapter.CardDesignHolder>(){

        inner class CardDesignHolder(cardDesignBinding: PromotionCarddesignBinding) :
                RecyclerView.ViewHolder(cardDesignBinding.root){
            val cardDesign: PromotionCarddesignBinding

            init {
                this.cardDesign = cardDesignBinding
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
            val layoutInflater = LayoutInflater.from(mContext)
            val design = PromotionCarddesignBinding.inflate(layoutInflater,parent,false)
            return CardDesignHolder(design)
        }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {

        var discount = listOf<Double>(79.88,25.99,28.99)
        holder.cardDesign.textViewPromotionPrice.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
        val promotion = promotionList.get(position)

        var url = "https://docs.google.com/uc?id="+promotion.urun_gorsel_url
        var id =holder.cardDesign.imageViewPromotionProducts
        Picasso.get().load(url).into(id)

        holder.cardDesign.textViewDiscountPrice.text = discount[position].toString() + "₺"

        holder.cardDesign.buttonPromotion.setOnClickListener {
            viewModel.loadBasket(promotion.id,1)
            Snackbar.make(it," Add to basket", Snackbar.LENGTH_SHORT).show()
        }

        holder.cardDesign.imageViewPromotionProducts.setOnClickListener {
           val pass = PromotionFragmentDirections.promotionToDetailPromotion(objPromotion = promotion)
           Navigation.findNavController(it).navigate(pass)
        }

        holder.cardDesign.promotionObject = promotion

    }


    override fun getItemCount(): Int {
            return promotionList.size
        }



}