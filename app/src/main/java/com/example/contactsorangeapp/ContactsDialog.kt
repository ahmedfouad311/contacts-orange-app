package com.example.contactsorangeapp

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.contacts_dialog.*


class ContactsDialog : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.contacts_dialog)
        this.setFinishOnTouchOutside(true)

        val name = intent.getStringExtra("added Name")
        val phone = intent.getStringExtra("added Phone")

        tvAddedName.text = "Name: $name"
        tvAddedNumber.text = "Number: $phone"


        btnOk.setOnClickListener {
            finish()
        }

    }
}