package com.bakitgulbirlik.capstoneproject.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bakitgulbirlik.capstoneproject.MainActivity
import com.bakitgulbirlik.capstoneproject.R
import com.bakitgulbirlik.capstoneproject.databinding.FragmentLogInBinding
import com.bakitgulbirlik.capstoneproject.entity.CRUDAnswer
import com.bakitgulbirlik.capstoneproject.entity.UsersAnswer
import com.bakitgulbirlik.capstoneproject.retrofit.ApiUtils
import com.bakitgulbirlik.capstoneproject.retrofit.UsersDaoInterface
import com.bakitgulbirlik.capstoneproject.viewmodel.LogInFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_log_in_main.*
import kotlinx.android.synthetic.main.fragment_log_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInFragment : Fragment() {
   private lateinit var design : FragmentLogInBinding
    private lateinit var udaoi : UsersDaoInterface


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_log_in, container, false)
        udaoi = ApiUtils.getUsersDaoInterface()

       design.buttonSingUpEntryPage.setOnClickListener {
           Navigation.findNavController(it).navigate(R.id.loginToSignUp)

        }



        design.buttonLogIn.setOnClickListener {
            validate()
            usersCall(design.editTextEmailAdress.text.toString(),design.editTextPassword.text.toString())
        }


        return design.root
    }



    fun usersCall(mail_adres: String,sifre: String) {
        udaoi.usersSelect(mail_adres,sifre).enqueue(object : Callback<UsersAnswer> {
            override fun onResponse(call: Call<UsersAnswer>?, response: Response<UsersAnswer>) {
                val usersList= response.body().bakitgulusers

                for(i in usersList){
                    if(i.deger==1){
                       showSuccess()
                        val sharedPreferences = context?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                        val editor = sharedPreferences?.edit()
                        editor?.apply {
                            for (i in usersList) {
                                putString("STRING_NAME", i.ad_soyad)
                                putString("STRING_MAİL", i.mail_adres)
                                putString("STRING_PHONE", i.telefon)
                            }
                        }?.apply()
                        Toast.makeText(requireContext(), "data saved", Toast.LENGTH_LONG).show()

                    }else if(i.deger==0){
                        Log.e("1","giriş yapılamadı")
                        Toast.makeText(requireContext(),"You Can NOT Entry",Toast.LENGTH_LONG).show()
                    }
                }
            }
            override fun onFailure(call: Call<UsersAnswer>?, t: Throwable?) {} })
    }



    private fun validate() {
        val isEmailValid = validateEmail()
        val isPassword = validatePassword()

    }

    private fun validateEmail():Boolean{
        val email = editTextEmailAdress.text.toString()
        if(email.isEmpty()){
            textInputLayoutEmail.error = getString(R.string.email_empty_error)
            return false
        }
        if(email.contains(other = "@").not()){
            textInputLayoutEmail.error = getString(R.string.email_invalid_error)
            return false
        }
        textInputLayoutEmail.error = null
        return true
    }

    private fun validatePassword():Boolean{
        val password = editTextPassword.text.toString()
        if(password.length<8){
            textInputLayoutPassword.error = getString(R.string.email_invalid_password)
            return false
        }
        textInputLayoutPassword.error = null
        return true
    }

    private fun showSuccess() {
        val successMessage = getString(R.string.login_success_message)
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        }
        //toast(successMessage)

}

