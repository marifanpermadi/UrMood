package com.example.urmood.presentation.ui.ui.model

data class UserResponse(
    val users: List<User>
)

data class User(
    val Idusers: Int,
    val fullname: String,
    val email: String,
    val password: String
)
