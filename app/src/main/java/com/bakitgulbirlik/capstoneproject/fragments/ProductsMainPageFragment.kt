package com.bakitgulbirlik.capstoneproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import com.bakitgulbirlik.capstoneproject.R
import com.bakitgulbirlik.capstoneproject.adapter.ProductsAdapter
import com.bakitgulbirlik.capstoneproject.databinding.FragmentProductsMainPageBinding
import com.bakitgulbirlik.capstoneproject.entity.Products
import com.bakitgulbirlik.capstoneproject.retrofit.ApiUtils
import com.bakitgulbirlik.capstoneproject.retrofit.ProductsDaoInterface
import com.bakitgulbirlik.capstoneproject.viewmodel.ProductsMainPageFragmentViewModel
import java.util.ArrayList

class ProductsMainPageFragment : Fragment() {
    private lateinit var design : FragmentProductsMainPageBinding
    private lateinit var adaper : ProductsAdapter
    private lateinit var pdaoi : ProductsDaoInterface
    private lateinit var productsList: ArrayList<Products>
    private lateinit var viewModel : ProductsMainPageFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_products_main_page, container, false)
        design.productsFragment = this
        pdaoi =ApiUtils.getProductsDaoInterface()

        val tempViewModel: ProductsMainPageFragmentViewModel by viewModels()
        this.viewModel = tempViewModel

        viewModel.productsList.observe(viewLifecycleOwner) { productsList ->
            adaper = ProductsAdapter(requireContext(),productsList, viewModel)
            design.productsAdapter = adaper
        }



        design.imageViewProductToBasket.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.productsToBasket)
        }

        return design.root
    }
}