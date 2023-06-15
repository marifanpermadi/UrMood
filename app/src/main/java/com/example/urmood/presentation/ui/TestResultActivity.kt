package com.example.urmood.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urmood.R
import com.example.urmood.databinding.ActivityTestResultBinding
import com.example.urmood.presentation.ui.ui.model.Contact
import com.example.urmood.presentation.ui.ui.model.ContactListAdapter

class TestResultActivity : AppCompatActivity() {

    private val list = ArrayList<Contact>()
    private lateinit var binding: ActivityTestResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when (intent.getStringExtra("predictedStressLevel")) {
            "Light Stress" -> {
                binding.ivResult.setImageResource(R.drawable.light_stress)
                binding.tvResult.text = getString(R.string.light_stress)
            }
            "Medium Stress" -> {
                binding.ivResult.setImageResource(R.drawable.med_stress)
                binding.tvResult.text = getString(R.string.med_stress)
            }
            "Heavy Stress" -> {
                binding.ivResult.setImageResource(R.drawable.heavy_stress)
                binding.tvResult.text = getString(R.string.heavy_stress)
            }
            else -> {
                binding.ivResult.setImageResource(R.drawable.baseline_question_mark_24)
                binding.tvResult.text = getString(R.string.unknown_stress)
            }
        }

        supportActionBar?.hide()

        binding.rvContact.setHasFixedSize(true)
        list.addAll(getListContact())
        showRecycleList()

        binding.btHome.setOnClickListener {
            val intent = Intent(this@TestResultActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showRecycleList() {
        binding.rvContact.layoutManager = LinearLayoutManager(this)
        val listContactAdapter = ContactListAdapter(list)
        binding.rvContact.adapter = listContactAdapter
    }

    @SuppressLint("Recycle")
    private fun getListContact(): ArrayList<Contact> {
        val dataName = resources.getStringArray(R.array.contact_name)
        val dataNumber = resources.getStringArray(R.array.contact_number)
        val dataPhoto = resources.obtainTypedArray(R.array.contact_photo)
        val dataDesc = resources.getStringArray(R.array.contact_desc)

        val listContact = ArrayList<Contact>()

        for (i in dataName.indices) {
            val contact = Contact(dataName[i], dataPhoto.getResourceId(i, -1), dataNumber[i], dataDesc[i])
            listContact.add(contact)
        }
        return listContact
    }
}