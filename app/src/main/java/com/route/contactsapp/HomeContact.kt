package com.route.contactsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.route.contactsapp.databinding.ActivityHomeContactBinding

class HomeContact : AppCompatActivity() {
    lateinit var binding: ActivityHomeContactBinding
    lateinit var adapter: ContactAdapter
    lateinit var name: String
    lateinit var phoneNumber: String
    lateinit var email: String
    lateinit var description: String
    var contacts = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        setClickOnSaveBtn()

    }

    private fun initRecyclerView() {
        adapter = ContactAdapter(contacts)
        binding.rvContacts.adapter = adapter
        adapter.onContactClickListener = ContactAdapter.OnContactClickListener {
            navigateToContactDetailsActivity(it)
        }
    }

    private fun navigateToContactDetailsActivity(contact: Contact) {
        val intent = Intent(this@HomeContact, ContactDetailsActivity::class.java)
        intent.putExtra(Constants.Contact, contact)
        startActivity(intent)
    }

    private fun setClickOnSaveBtn() {

        binding.btnSave.setOnClickListener {

            if (!validateTextFields())
                return@setOnClickListener

            name = binding.edtName.text?.trim().toString()
            phoneNumber = binding.edtPhoneNumber.text?.trim().toString()
            email = binding.edtEmail.text?.trim().toString()
            description = binding.edtDescription.text?.trim().toString()

            val contact = Contact(name, phoneNumber, email, description)
            contacts.add(contact)
            adapter.notifyItemInserted(contacts.size - 1)
        }

    }

    private fun validateTextFields(): Boolean {
        name = binding.edtName.text?.trim().toString()
        phoneNumber = binding.edtPhoneNumber.text?.trim().toString()
        binding.nameLayout.error = validateName(name)
        binding.phoneLayout.error = validatePhoneNumber(phoneNumber)
        return binding.nameLayout.error == null && binding.phoneLayout.error == null
    }

    private fun validatePhoneNumber(phoneNumber: String): String? {
        if (phoneNumber.isEmpty())
            return "Required"
        if (phoneNumber.length < 11)
            return "Phone number can't be less than 11 digits"
        return null
    }

    private fun validateName(name: String): String? {
        if (name.isEmpty())
            return "Required"
        if (name.length < 3)
            return "Name can't be less than 3 characters"
        return null
    }
}