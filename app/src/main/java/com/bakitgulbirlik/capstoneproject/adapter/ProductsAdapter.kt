package com.bakitgulbirlik.capstoneproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bakitgulbirlik.capstoneproject.databinding.CardDesignBinding
import com.bakitgulbirlik.capstoneproject.entity.Products
import com.bakitgulbirlik.capstoneproject.fragments.ProductsMainPageFragmentDirections
import com.bakitgulbirlik.capstoneproject.viewmodel.ProductsMainPageFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class ProductsAdapter(var mContext: Context, var productsList:List<Products>, var viewModel: ProductsMainPageFragmentViewModel)
    :RecyclerView.Adapter<ProductsAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(cardDesignBinding: CardDesignBinding) :
            RecyclerView.ViewHolder(cardDesignBinding.root){
        val cardDesign: CardDesignBinding

        init {
            this.cardDesign = cardDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val design = CardDesignBinding.inflate(layoutInflater,parent,false)
        return CardDesignHolder(design)
    }


    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val product = productsList.get(position)
        val d = holder.cardDesign

        d.productsObject = product

        var url = "https://docs.google.com/uc?id="+product.urun_gorsel_url
        var id =holder.cardDesign.imageViewCardDesign
        Picasso.get().load(url).into(id)

        holder.cardDesign.imageViewAdd.setOnClickListener {
            viewModel.loadBasket(product.id,1)
            Snackbar.make(it," Add to basket", Snackbar.LENGTH_SHORT).show()
        }

        holder.cardDesign.imageViewCardDesign.setOnClickListener {
            val pass = ProductsMainPageFragmentDirections.productsToDetailAction(objectProducts = product)
            Navigation.findNavController(it).navigate(pass)
        }

        holder.cardDesign.imageViewInfo.setOnClickListener {
            val pass = ProductsMainPageFragmentDirections.productsToDetailAction(objectProducts = product)
            Navigation.findNavController(it).navigate(pass)
        }



    }

    override fun getItemCount(): Int {
        return productsList.size
    }



}