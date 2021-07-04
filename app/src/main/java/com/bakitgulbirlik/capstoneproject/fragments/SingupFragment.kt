package com.bakitgulbirlik.capstoneproject.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bakitgulbirlik.capstoneproject.R
import com.bakitgulbirlik.capstoneproject.databinding.FragmentSingupBinding
import com.bakitgulbirlik.capstoneproject.entity.CRUDAnswer
import com.bakitgulbirlik.capstoneproject.retrofit.ApiUtils
import com.bakitgulbirlik.capstoneproject.retrofit.UsersDaoInterface
import kotlinx.android.synthetic.main.fragment_log_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingupFragment : Fragment() {
    private lateinit var design : FragmentSingupBinding
    private lateinit var udaoi : UsersDaoInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_singup, container, false)
        udaoi = ApiUtils.getUsersDaoInterface()

        design.editTextEmail.text.toString()

        design.buttonSingUp.setOnClickListener {
            validate()
            SignupUser(design.editTextEmail.text.toString(),
                    design.editTextPassword.text.toString(),
                    design.editTextNameSurname.text.toString(),
                    design.editTextTelephone.text.toString().toInt())
        }

        return design.root
    }

    fun SignupUser(mail_adres:String,sifre:String,ad_soyad:String,telefon:Int){
        udaoi.userAdd(mail_adres,sifre, ad_soyad, telefon).enqueue(object: Callback<CRUDAnswer> {
            override fun onFailure(call: Call<CRUDAnswer>?, t: Throwable?) {}
            override fun onResponse(call: Call<CRUDAnswer>?, response: Response<CRUDAnswer>) {
                Log.e("Başarı",response.body().success.toString())
                Log.e("Mesaj",response.body().message)

            }
        })
    }

    private fun validate() {
        val isEmailValid = validateEmail()
        val isPassword = validatePassword()

        if (isEmailValid && isPassword) {
            showSuccess()
        }
    }

    private fun validateEmail():Boolean{
        val email = design.editTextEmail.text.toString()
        if(email.isEmpty()){
            design.textInputLayoutSingE.error = getString(R.string.email_empty_error)
            return false
        }
        if(email.contains(other = "@").not()){
            design.textInputLayoutSingE.error = getString(R.string.email_invalid_error)
            return false
        }
        design.textInputLayoutSingE.error = null
        return true
    }

    private fun validatePassword():Boolean{
        val password = design.editTextPassword.text.toString()
        if(password.length<8){
            design.textInputLayoutSingP.error = getString(R.string.email_invalid_password)
            return false
        }
        design.textInputLayoutSingP.error = null
        return true
    }

    private fun showSuccess(){
        val successMessage = getString(R.string.singup_success_message)
        toast(successMessage)

    }

    private fun toast(text:String){
        Toast.makeText(context,text, Toast.LENGTH_LONG).show()
    }

}
