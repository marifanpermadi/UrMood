package com.example.urmood.data.api

import com.example.urmood.presentation.ui.ui.model.ArticleResponse
import com.example.urmood.presentation.ui.ui.model.TipsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceArticle {
    @GET("article")
    fun getAllArticle(): Call<List<ArticleResponse>>

    @GET("alltips")
    fun getAllTips(): Call<List<TipsResponse>>
}