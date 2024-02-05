package com.route.contactsapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val name : String,
    val phoneNumber : String,
    val email : String,
    val description : String
):Parcelable
