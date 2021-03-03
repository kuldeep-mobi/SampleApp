package com.mobikasa.logisticapp.repositories

import androidx.lifecycle.LiveData
import com.mobikasa.logisticapp.network.APIService
import com.mobikasa.logisticapp.network.NetworkResource
import com.mobikasa.logisticapp.network.Resource

class LoginRepository(val service: APIService) {
    fun doSignIn(email: String, password: String): LiveData<Resource<String>> {
        return object : NetworkResource<String>() {
            override fun createCall(): LiveData<Resource<String>> {
                return service.doSignIn(email,password)
            }
        }.asLiveData()
    }
}