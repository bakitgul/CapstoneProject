package com.bakitgulbirlik.capstoneproject.fragments

import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bakitgulbirlik.capstoneproject.MainActivity
import com.bakitgulbirlik.capstoneproject.R
import com.bakitgulbirlik.capstoneproject.adapter.BasketAdapter
import com.bakitgulbirlik.capstoneproject.adapter.ProductsAdapter
import com.bakitgulbirlik.capstoneproject.databinding.FragmentBasketBinding
import com.bakitgulbirlik.capstoneproject.entity.Products
import com.bakitgulbirlik.capstoneproject.retrofit.ApiUtils
import com.bakitgulbirlik.capstoneproject.retrofit.ProductsDaoInterface
import com.bakitgulbirlik.capstoneproject.viewmodel.BasketFragmentViewModel
import com.squareup.picasso.Picasso
import java.util.ArrayList

class BasketFragment : Fragment() {

    private lateinit var viewModel : BasketFragmentViewModel
    private lateinit var design : FragmentBasketBinding
    private lateinit var adapter: BasketAdapter
    private lateinit var pdaoi : ProductsDaoInterface
    private lateinit var basketList: ArrayList<Products>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_basket, container, false)
        pdaoi = ApiUtils.getProductsDaoInterface()
        design.basketFragment = this

        viewModel.basketList.observe(viewLifecycleOwner) { basketList ->
            adapter = BasketAdapter(requireContext(),basketList, viewModel)
            design.basketAdapter = adapter
        }







        return design.root
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: BasketFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }

}
