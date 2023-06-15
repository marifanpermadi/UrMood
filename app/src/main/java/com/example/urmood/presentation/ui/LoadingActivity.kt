package com.example.urmood.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.urmood.R

class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        supportActionBar?.hide()
    }
}