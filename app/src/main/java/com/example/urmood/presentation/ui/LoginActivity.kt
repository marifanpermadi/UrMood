package com.example.urmood.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.urmood.R
import com.example.urmood.databinding.ActivityLoginBinding
import com.example.urmood.presentation.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.btLogin.setOnClickListener {
            val emailEditText = binding.etEmail
            val passwordEditText = binding.etPassword

            if (!emailEditText.isEmailValid(emailEditText.text.toString())) {
                Toast.makeText(this, getString(R.string.email_invalid), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!passwordEditText.isPasswordValid()) {
                Toast.makeText(this, getString(R.string.password_invalid), Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            login()
        }

        binding.btRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        supportActionBar?.hide()
    }

    private fun login() {
        binding.apply {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            viewModel.login(email, password)
            showLoading(true)

            viewModel.login.observe(this@LoginActivity) {
                showLoading(false)
                Toast.makeText(
                    this@LoginActivity,
                    getString(R.string.login_success),
                    Toast.LENGTH_LONG
                ).show()

                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }

            viewModel.error.observe(this@LoginActivity) {
                showLoading(false)
                Toast.makeText(
                    this@LoginActivity,
                    getString(R.string.login_fail),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}