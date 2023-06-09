package com.example.urmood.data.api

import com.example.urmood.data.model.LoginModel
import com.example.urmood.data.model.RegisterModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    fun register(@Body registerModel: RegisterModel): Call<RegisterModel?>?

    @POST("login")
    fun login(@Body loginModel: LoginModel): Call<LoginModel?>?

}