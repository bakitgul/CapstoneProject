package com.bakitgulbirlik.capstoneproject.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.bakitgulbirlik.capstoneproject.R
import com.bakitgulbirlik.capstoneproject.adapter.ProductsAdapter
import com.bakitgulbirlik.capstoneproject.databinding.FragmentProfileBinding
import com.bakitgulbirlik.capstoneproject.entity.Users
import com.bakitgulbirlik.capstoneproject.entity.UsersAnswer
import com.bakitgulbirlik.capstoneproject.retrofit.ApiUtils
import com.bakitgulbirlik.capstoneproject.retrofit.UsersDaoInterface
import com.bakitgulbirlik.capstoneproject.viewmodel.ProductsMainPageFragmentViewModel
import com.bakitgulbirlik.capstoneproject.viewmodel.ProfileFragmentViewModel
import kotlinx.android.synthetic.main.fragment_log_in.*
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private lateinit var design : FragmentProfileBinding
    private lateinit var viewModel : ProfileFragmentViewModel
    private lateinit var udaoi: UsersDaoInterface


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       design = DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false)
        udaoi = ApiUtils.getUsersDaoInterface()

        loadData()

        return design.root
    }

    fun loadData(){
        val sharedPreferences= context?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedName= sharedPreferences?.getString("STRING_NAME",null)
        val savedMail= sharedPreferences?.getString("STRING_MAIL",null)
        val savedPhone= sharedPreferences?.getString("STRING_PHONE",null)

        design.textViewProfileNameSurname.text=savedName
        design.textViewProfileMailAddress.text=savedMail
        design.textViewProfileTelephone.text=savedPhone

    }

}