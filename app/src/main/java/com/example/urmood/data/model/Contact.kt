package com.example.urmood.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val name: String,
    val photo: Int,
    val number: String,
    val desc: String
) : Parcelable