package com.example.contactsorangeapp

import android.content.Context
import android.content.Intent
import android.database.ContentObserver
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity

class ContactsObserver(context: Context) : ContentObserver(null) {
    private val mContext = context
    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        Log.e("Message", "Contacts changed")
        Log.e("Self Change", "$selfChange")
        if(selfChange){
            val builder = AlertDialog.Builder(mContext)
            val dialog: AlertDialog = builder.create()
            dialog.window!!.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT)
            builder.setTitle("Last added Contact")
            builder.setMessage("Action")
            dialog.show()
        }
        else{
            throw Exception("AHMED")
        }
    }

    override fun deliverSelfNotifications(): Boolean {
        return true
    }
}