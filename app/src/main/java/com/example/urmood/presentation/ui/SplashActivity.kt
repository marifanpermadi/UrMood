package com.example.urmood.presentation.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import com.example.urmood.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        playAnimation()

        Handler(Looper.getMainLooper()).postDelayed({
            //TODO("Check logged in user, go to login if no user logged in else go to home activity")
            val intent = Intent(this@SplashActivity, OnboardingActivity::class.java)
            startActivity(intent)
            finish()
        }, delayTime)
    }

    private fun playAnimation() {

        val logo = ObjectAnimator.ofFloat(binding.ivLogo, View.ALPHA, 1f).setDuration(500)
        val app = ObjectAnimator.ofFloat(binding.tvApp, View.ALPHA, 1f).setDuration(500)
        val forecast = ObjectAnimator.ofFloat(binding.tvForecast, View.ALPHA, 1f).setDuration(500)
        val line = ObjectAnimator.ofFloat(binding.ivForecast, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(logo, app, forecast, line)
            start()
        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION") if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    companion object {
        const val delayTime: Long = 4000
    }
}