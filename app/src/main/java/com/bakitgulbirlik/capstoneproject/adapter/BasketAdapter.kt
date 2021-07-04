package com.bakitgulbirlik.capstoneproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.bakitgulbirlik.capstoneproject.databinding.BasketCarddesignBinding
import com.bakitgulbirlik.capstoneproject.entity.Products
import com.bakitgulbirlik.capstoneproject.fragments.BasketFragmentArgs
import com.bakitgulbirlik.capstoneproject.fragments.ProductsDetailPageFragmentDirections
import com.bakitgulbirlik.capstoneproject.viewmodel.BasketFragmentViewModel
import com.bakitgulbirlik.capstoneproject.viewmodel.ProductsMainPageFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class BasketAdapter (var mContext: Context, var basketList:List<Products>, var viewModel: BasketFragmentViewModel)
    :RecyclerView.Adapter<BasketAdapter.CardDesignHolder>(){
        inner class CardDesignHolder(cardDesignBinding: BasketCarddesignBinding) :
                RecyclerView.ViewHolder(cardDesignBinding.root) {
            val cardDesign: BasketCarddesignBinding

            init {
                this.cardDesign = cardDesignBinding
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val layoutInflater =LayoutInflater.from(mContext)
        val design = BasketCarddesignBinding.inflate(layoutInflater, parent, false)
        return CardDesignHolder(design)
    }



    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val basket = basketList.get(position)
        holder.cardDesign.basketobject =basket


        var url = "https://docs.google.com/uc?id="+basket.urun_gorsel_url
        var id =holder.cardDesign.imageViewBasket
        Picasso.get().load(url).into(id)

        holder.cardDesign.imageViewDelete.setOnClickListener {
            viewModel.updateBasket(basket.id,0)
            viewModel.loadBasket()
            Snackbar.make(it,"Delete to basket", Snackbar.LENGTH_SHORT).show()
        }



    }

    override fun getItemCount(): Int {
        return basketList.size
    }






}