package com.bakitgulbirlik.capstoneproject.repo

import androidx.lifecycle.MutableLiveData
import com.bakitgulbirlik.capstoneproject.entity.*
import com.bakitgulbirlik.capstoneproject.retrofit.ApiUtils
import com.bakitgulbirlik.capstoneproject.retrofit.UsersDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersdaoRepository {
    private val usersList: MutableLiveData<List<Users>>
    private val udaoi: UsersDaoInterface

    init{
        udaoi = ApiUtils.getUsersDaoInterface()
        usersList = MutableLiveData()
    }

    fun getAllUsers(){
        udaoi.usersSelect("b.fatmabirlik@gmail.com","Ertu35BFB").enqueue(object : Callback<UsersAnswer>{
            override fun onResponse(call: Call<UsersAnswer>?, response: Response<UsersAnswer>?) {
                val users = response?.body()?.bakitgulusers
                usersList.value = users

            }
            override fun onFailure(call: Call<UsersAnswer>?, t: Throwable?) {}
        })
    }

    fun getUsers(): MutableLiveData<List<Users>>{
        return usersList
    }
}