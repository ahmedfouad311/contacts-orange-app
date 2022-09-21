package com.example.contactsorangeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.contacts_dialog.*


class ContactsDialog : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contacts_dialog)
        this.setFinishOnTouchOutside(true)

        val intent = intent
        val addedContact = intent.getParcelableArrayListExtra<Contacts>("contact") as ArrayList<Contacts>
        val lastContact = addedContact.lastIndex
        tvAddedContact.text = "$lastContact"

        btnOk.setOnClickListener {
            finish()
        }

    }
}