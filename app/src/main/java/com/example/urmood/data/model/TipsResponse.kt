package com.example.urmood.data.model

import com.google.gson.annotations.SerializedName

data class TipsResponse(

    @field:SerializedName("Id")
    val id: Int,

    @field:SerializedName("tips")
    val tips: String
)
