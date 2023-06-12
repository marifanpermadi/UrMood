package com.example.urmood.data.api

import com.example.urmood.data.model.LoginModel
import com.example.urmood.data.model.LogoutResponse
import com.example.urmood.data.model.RegisterModel
import com.example.urmood.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("register")
    fun register(@Body registerModel: RegisterModel): Call<RegisterModel?>?

    @POST("login")
    fun login(@Body loginModel: LoginModel): Call<LoginModel?>?

    @GET("usersdata")
    fun getUserData(@Query("email") email: String): Call<UserResponse>

    @POST("logout")
    fun logout(@Query("email") email: String): Call<LogoutResponse>

}