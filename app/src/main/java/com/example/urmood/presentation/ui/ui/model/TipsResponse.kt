package com.example.urmood.presentation.ui.ui.model

import com.google.gson.annotations.SerializedName

data class TipsResponse(

	@field:SerializedName("TipsResponse")
	val tipsResponse: List<TipsResponseItem?>? = null
)

data class TipsResponseItem(

	@field:SerializedName("Id")
	val id: Int? = null,

	@field:SerializedName("tips")
	val tips: String? = null
)
