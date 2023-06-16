package com.example.urmood.presentation.ui.ui.model

import android.content.Context
import android.content.SharedPreferences

object UserSession {

    private const val PREF_NAME = "user_session"
    private const val KEY_LOGGED_IN_USER_EMAIL = "logged_in_user_email"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    var loggedInUserEmail: String
        get() = sharedPreferences.getString(KEY_LOGGED_IN_USER_EMAIL, "") ?: ""
        set(value) = sharedPreferences.edit().putString(KEY_LOGGED_IN_USER_EMAIL, value).apply()

    var isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) = sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, value).apply()
}