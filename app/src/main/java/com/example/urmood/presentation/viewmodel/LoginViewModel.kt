package com.example.urmood.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.urmood.data.api.ApiConfig
import com.example.urmood.data.model.LoginModel
import com.example.urmood.data.model.UserSession
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _login = MutableLiveData<LoginModel>()
    val login: LiveData<LoginModel> = _login

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun login(email: String, password: String) {
        val loginModel = LoginModel(email, password)
        val call: Call<LoginModel?>? = ApiConfig.getApiService().login(loginModel)
        call!!.enqueue(object : Callback<LoginModel?> {
            override fun onResponse(
                call: Call<LoginModel?>,
                response: Response<LoginModel?>
            ) {
                if (response.isSuccessful) {
                    val responseBody: LoginModel? = response.body()
                    responseBody?.let {
                        _login.value = it
                        //_email.value = email
                        UserSession.loggedInUserEmail = email
                        UserSession.isLoggedIn = true
                    }
                } else {
                    _error.value = "Login failed"
                }
            }

            override fun onFailure(call: Call<LoginModel?>, t: Throwable) {
                _error.value = t.message
            }
        })
    }
}