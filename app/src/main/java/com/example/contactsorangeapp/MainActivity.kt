package com.example.contactsorangeapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var contactsAdapter: ContactsAdapter

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactsAdapter = ContactsAdapter(mutableListOf())

        rvContactsList.adapter = contactsAdapter
        rvContactsList.layoutManager = LinearLayoutManager(this)

        btnReadContacts.setOnClickListener{
            val contactList : MutableList<Contacts> = ArrayList()
            val contactsObj = Contacts()
            val contacts = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null, null)
            while(contacts!!.moveToNext()){
                val name = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contactsObj.name = name
                contactsObj.number = number
                contactList.add(contactsObj)
            }
            rvContactsList.adapter = ContactsAdapter(contactList)
            contacts.close()
        }
    }
}