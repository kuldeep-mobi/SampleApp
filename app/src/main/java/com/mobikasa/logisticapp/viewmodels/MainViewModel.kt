package com.mobikasa.logisticapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobikasa.logisticapp.repositories.LoginRepository

class MainViewModel(mLoginRepository: LoginRepository) : ViewModel() {
    val title = MutableLiveData<String>()
    fun isLogin():Boolean{
        return true
    }



}