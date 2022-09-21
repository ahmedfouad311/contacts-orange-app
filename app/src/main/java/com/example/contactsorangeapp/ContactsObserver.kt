package com.example.contactsorangeapp

import android.content.Context
import android.content.Intent
import android.database.ContentObserver
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity

class ContactsObserver : ContentObserver(null) {
    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        Log.e("Message", "Contacts changed")
//        val builder = AlertDialog.Builder(mContext)
//        builder.setTitle("Contacts Changed")
//        builder.setMessage("Action")
//        builder.show()
    }

    override fun deliverSelfNotifications(): Boolean {
        return true
    }
}