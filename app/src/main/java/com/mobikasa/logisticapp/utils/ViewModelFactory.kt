package com.mobikasa.logisticapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobikasa.logisticapp.repositories.LoginRepository
import com.mobikasa.logisticapp.viewmodels.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val mLoginRepository: LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mLoginRepository) as T
        }
        throw RuntimeException("Invalid ViewModel Name..")
    }
}