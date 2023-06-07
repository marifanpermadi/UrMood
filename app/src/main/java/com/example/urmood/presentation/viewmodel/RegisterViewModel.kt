package com.example.urmood.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.urmood.data.api.ApiConfig
import com.example.urmood.presentation.utils.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel: ViewModel() {
    private val _register = MutableLiveData<Result<RegisterResponse>>()
    val register: LiveData<Result<RegisterResponse>> = _register

    fun register(fullname: String, email: String, password: String) {
        val client = ApiConfig.getApiService().register(fullname, email, password)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    _register.value = Result.Success(response.body()!!)
                } else {
                    _register.value = Result.Failure(response.message().toString())
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _register.value = Result.Failure(t.message)
            }
        })
    }

}