package com.example.urmood.presentation.ui.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.urmood.data.api.ApiConfig
import com.example.urmood.presentation.ui.ui.model.LogoutResponse
import com.example.urmood.presentation.ui.ui.model.User
import com.example.urmood.presentation.ui.ui.model.UserResponse
import com.example.urmood.presentation.ui.ui.model.UserSession
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationsViewModel : ViewModel() {

    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User> = _userData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _logout = MutableLiveData<LogoutResponse>()
    val logout : LiveData<LogoutResponse> = _logout

    fun getUserData(email: String) {
        val client = ApiConfig.getApiService().getUserData(email)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    val user = userResponse?.users?.find { it.email == email }
                    user?.let {
                        _userData.value = it
                    }
                } else {
                    _error.value = "Failed to get user data"
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _error.value = t.message
            }
        })
    }

    fun logout(email: String) {
        val client = ApiConfig.getApiService().logout(email)
        client.enqueue(object : Callback<LogoutResponse> {
            override fun onResponse(
                call: Call<LogoutResponse>,
                response: Response<LogoutResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody: LogoutResponse? = response.body()
                    responseBody?.let {
                        _logout.value = it
                        UserSession.loggedInUserEmail = ""
                        UserSession.isLoggedIn = false
                    }

                } else {
                    _error.value = "Failed to log out user"
                }
            }

            override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                _error.value = t.message
            }
        })
    }
}