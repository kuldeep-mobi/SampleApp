package com.mobikasa.logisticapp.network

import androidx.lifecycle.LiveData
import retrofit2.http.Field
import retrofit2.http.POST

interface APIService {
    @POST("signIn")
    fun doSignIn(
        @Field("email") email: String,
        @Field("password") password: String
    ): LiveData<Resource<String>>
}