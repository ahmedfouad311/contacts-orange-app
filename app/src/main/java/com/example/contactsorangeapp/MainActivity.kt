package com.example.contactsorangeapp

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.ContentObserver
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var contactsAdapter: ContactsAdapter
    private lateinit var receiver: BroadcastReceiver

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        receiver = ContactReceiver()
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)

        registerReceiver(receiver, filter)


        contactsAdapter = ContactsAdapter(mutableListOf())

        rvContactsList.adapter = contactsAdapter
        rvContactsList.layoutManager = LinearLayoutManager(this)

        btnReadContacts.setOnClickListener {
            val contactList: MutableList<Contacts> = ArrayList()
            val contactsObj = Contacts()
            val contacts = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                ),
                ContactsContract.Contacts.HAS_PHONE_NUMBER + ">0 AND LENGTH(" + ContactsContract.CommonDataKinds.Phone.NUMBER + ")>0",
                null,
                "display_name ASC"
            )
            while (contacts!!.moveToNext()) {
                val name =
                    contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number =
                    contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contactsObj.name = name
                contactsObj.number = number
                contactList.add(contactsObj)
            }
            rvContactsList.adapter = ContactsAdapter(contactList)
            contacts.close()

            contentResolver.registerContentObserver(
                ContactsContract.Contacts.CONTENT_URI, false, ContactsObserver(this@MainActivity)
            )
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}
