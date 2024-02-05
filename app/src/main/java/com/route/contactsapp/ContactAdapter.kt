package com.route.contactsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.contactsapp.databinding.ContactItemBinding

class ContactAdapter(var contactsList: List<Contact>? = null) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    var onContactClickListener: OnContactClickListener? = null

    class ViewHolder(val itemBinding: ContactItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding: ContactItemBinding =
            ContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = contactsList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactsList!![position]
        holder.itemBinding.tvNameItem.text = contact.name
        holder.itemBinding.tvPhoneItem.text = contact.phoneNumber
        holder.itemView.setOnClickListener {
            onContactClickListener?.onClick(contact = contact)
        }
    }

    fun interface OnContactClickListener {
        fun onClick(contact: Contact)
    }
}