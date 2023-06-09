package com.example.urmood.data.model

import com.google.gson.annotations.SerializedName

data class ArticleResponse(

    @field:SerializedName("link")
    val link: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("body")
    val body: String
)
