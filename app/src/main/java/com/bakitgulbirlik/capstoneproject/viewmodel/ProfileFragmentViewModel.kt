package com.bakitgulbirlik.capstoneproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bakitgulbirlik.capstoneproject.entity.Users
import com.bakitgulbirlik.capstoneproject.repo.UsersdaoRepository

class ProfileFragmentViewModel:ViewModel() {
    var usersList = MutableLiveData<List<Users>>()
    private val udaor = UsersdaoRepository()

    init {
        loadUsers()
        usersList = udaor.getUsers()
    }

    fun loadUsers() {
        udaor.getAllUsers()
    }
}