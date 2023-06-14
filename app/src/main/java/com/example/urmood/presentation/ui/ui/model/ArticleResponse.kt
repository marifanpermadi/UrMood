package com.example.urmood.presentation.ui.ui.model

import com.google.gson.annotations.SerializedName

data class ArticleResponse(

	@field:SerializedName("ArticleResponse")
	val articleResponse: List<ArticleResponseItem?>? = null
)

data class ArticleResponseItem(

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val body: String? = null
)
