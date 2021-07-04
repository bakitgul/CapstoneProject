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
import com.bakitgulbirlik.capstoneproject.adapter.PromotionAdapter
import com.bakitgulbirlik.capstoneproject.databinding.FragmentProductsMainPageBinding
import com.bakitgulbirlik.capstoneproject.databinding.FragmentPromotionBinding
import com.bakitgulbirlik.capstoneproject.entity.Products
import com.bakitgulbirlik.capstoneproject.viewmodel.ProductsMainPageFragmentViewModel
import com.bakitgulbirlik.capstoneproject.viewmodel.PromotionFragmentViewModel
import java.util.ArrayList
import android.graphics.Paint


class PromotionFragment : Fragment() {
    private lateinit var design: FragmentPromotionBinding
    private lateinit var adaper: PromotionAdapter
    private lateinit var promotionList: ArrayList<Products>
    private lateinit var viewModel: PromotionFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{

        design = DataBindingUtil.inflate(inflater,R.layout.fragment_promotion, container, false)
        design.promotionFragment = this

       val tempViewModel: PromotionFragmentViewModel by viewModels()
        this.viewModel = tempViewModel

        viewModel.promotionList.observe(viewLifecycleOwner) { promotionList ->
            adaper = PromotionAdapter(requireContext(),promotionList, viewModel)
            design.promotionAdapter = adaper

        }

        return design.root
    }


}
