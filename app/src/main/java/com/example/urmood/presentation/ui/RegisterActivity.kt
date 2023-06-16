package com.example.urmood.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.urmood.R
import com.example.urmood.databinding.ActivityRegisterBinding
import com.example.urmood.presentation.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        binding.btRegister.setOnClickListener {
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
            register()
        }

        binding.btLogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        supportActionBar?.hide()
    }

    private fun register() {
        binding.apply {
            val fullname = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            viewModel.register(fullname, email, password)
            showLoading(true)

            viewModel.register.observe(this@RegisterActivity) {
                showLoading(false)
                Toast.makeText(
                    this@RegisterActivity,
                    getString(R.string.register_succeed),
                    Toast.LENGTH_LONG
                ).show()

                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

            viewModel.error.observe(this@RegisterActivity) {
                showLoading(false)
                Toast.makeText(
                    this@RegisterActivity,
                    getString(R.string.register_fail),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}