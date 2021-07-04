package com.bakitgulbirlik.capstoneproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bakitgulbirlik.capstoneproject.entity.Users
import com.bakitgulbirlik.capstoneproject.repo.UsersdaoRepository

class LogInFragmentViewModel:ViewModel() {
    val udaor = UsersdaoRepository()
    var success = MutableLiveData<Int>()
    var user = MutableLiveData<List<Users>>()

    init {
        //success = udaor.getAllUsers()
        user = udaor.getUsers()
    }


}