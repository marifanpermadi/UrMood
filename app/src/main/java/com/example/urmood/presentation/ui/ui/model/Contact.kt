package com.example.urmood.presentation.ui.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact (
    val name: String,
    val photo: Int,
    val number: String
): Parcelable