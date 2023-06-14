package com.example.urmood.presentation.ui.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.urmood.data.api.ApiConfig
import com.example.urmood.presentation.ui.ui.model.ArticleResponse
import com.example.urmood.presentation.ui.ui.model.TipsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _listTips = MutableLiveData<TipsResponse>()
    val listTips : LiveData<TipsResponse> = _listTips

    private val _listArticle = MutableLiveData<ArticleResponse>()
    val listArticle : LiveData<ArticleResponse> = _listArticle

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    init {
        getAllTips()
        getAllArticle()
    }

    private fun getAllTips(){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getAllTips()
        client.enqueue(object : Callback<TipsResponse> {
            override fun onResponse(call: Call<TipsResponse>, response: Response<TipsResponse>) {
                _isLoading.value = false
                if (response.isSuccessful){
                    val responseBody: TipsResponse? = response.body()
                    responseBody.let {
                        _listTips.value = it
                    }

                }
                else{
                    _error.value = "Failed to get Tips"
                }
            }

            override fun onFailure(call: Call<TipsResponse>, t: Throwable) {
                _isLoading.value = false
                _error.value = t.message
            }
        })
    }

    private fun getAllArticle(){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getAllArticle()
        client.enqueue(object : Callback<ArticleResponse>{
            override fun onResponse(
                call: Call<ArticleResponse>,
                response: Response<ArticleResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    val responseBody: ArticleResponse? = response.body()
                    responseBody.let {
                        _listArticle.value = it
                    }
                }
                else{
                    _error.value = "Failed to get Article"
                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                _isLoading.value = false
                _error.value = t.message
            }
        })
    }
}