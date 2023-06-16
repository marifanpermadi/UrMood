package com.example.urmood.presentation.ui.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.urmood.data.api.ApiConfigArticle
import com.example.urmood.presentation.ui.ui.model.ArticleResponse
import com.example.urmood.presentation.ui.ui.model.TipsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _listTips = MutableLiveData<List<TipsResponse>>()
    val listTips: LiveData<List<TipsResponse>> = _listTips

    private val _listArticle = MutableLiveData<List<ArticleResponse>>()
    val listArticle: LiveData<List<ArticleResponse>> = _listArticle

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getAllTips()
        getAllArticle()
    }

    private fun getAllTips() {
        _isLoading.value = true
        val client = ApiConfigArticle.getApiService().getAllTips()
        client.enqueue(object : Callback<List<TipsResponse>> {
            override fun onResponse(
                call: Call<List<TipsResponse>>,
                response: Response<List<TipsResponse>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val listTips = response.body() as List<TipsResponse>
                    _listTips.value = listTips
                } else {
                    _error.value = "Failed to get Tips"
                }
            }

            override fun onFailure(call: Call<List<TipsResponse>>, t: Throwable) {
                _isLoading.value = false
                _error.value = t.message
            }
        })
    }

    private fun getAllArticle() {
        _isLoading.value = true
        val client = ApiConfigArticle.getApiService().getAllArticle()
        client.enqueue(object : Callback<List<ArticleResponse>> {
            override fun onResponse(
                call: Call<List<ArticleResponse>>,
                response: Response<List<ArticleResponse>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val listArticle = response.body() as List<ArticleResponse>
                    _listArticle.value = listArticle
                } else {
                    _error.value = "Failed to get Article"
                }
            }

            override fun onFailure(call: Call<List<ArticleResponse>>, t: Throwable) {
                _isLoading.value = false
                _error.value = t.message
            }
        })
    }
}