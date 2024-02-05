package com.route.contactsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.route.contactsapp.databinding.ActivityContactDetailsBinding

class ContactDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityContactDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupContactViews()

    }

    private fun setupContactViews() {
        val contact =
            IntentCompat.getParcelableExtra(intent, Constants.Contact, Contact::class.java)
        contact?.let {
            binding.tvName.text = it.name
            binding.tvDescription.text = it.description
            binding.tvEmailValue.text = it.email
            binding.tvNumberValue.text = it.phoneNumber
            binding.tvWhatsappValue.text = it.phoneNumber
            binding.ivBackArrow.setOnClickListener {
                onBackPressed()
            }
        }
    }
}