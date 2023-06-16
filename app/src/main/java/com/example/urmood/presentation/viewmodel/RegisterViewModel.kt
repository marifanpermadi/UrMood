package com.example.urmood.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.urmood.data.api.ApiConfig
import com.example.urmood.presentation.ui.ui.model.RegisterModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    private val _register = MutableLiveData<RegisterModel>()
    val register: LiveData<RegisterModel> = _register

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun register(fullname: String, email: String, password: String) {
        val registerModel = RegisterModel(fullname, email, password)
        val call: Call<RegisterModel?>? = ApiConfig.getApiService().register(registerModel)
        call!!.enqueue(object : Callback<RegisterModel?> {

            override fun onResponse(
                call: Call<RegisterModel?>,
                response: Response<RegisterModel?>
            ) {
                if (response.isSuccessful) {
                    val responseBody: RegisterModel? = response.body()
                    responseBody?.let {
                        _register.value = it
                    }
                } else {
                    _error.value = "Registration failed"
                }
            }

            override fun onFailure(call: Call<RegisterModel?>, t: Throwable) {
                _error.value = t.message
            }
        })
    }
}