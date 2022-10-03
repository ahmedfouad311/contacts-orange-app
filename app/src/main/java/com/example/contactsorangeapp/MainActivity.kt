package com.example.contactsorangeapp

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var receiver: BroadcastReceiver
    private lateinit var contactsObj: Contacts
    var checkContacts = true

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        receiver = ContactReceiver()
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)

        registerReceiver(receiver, filter)


        rvContactsList.layoutManager = LinearLayoutManager(this)

        btnReadContacts.setOnClickListener {
            val contactList: MutableList<Contacts> = ArrayList()
            val contacts = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null,
            )
            while (contacts!!.moveToNext()) {
                val name =
                    contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number =
                    contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contactsObj = Contacts()
                contactsObj.name = name
                contactsObj.number = number
                contactList.add(contactsObj)
            }
            rvContactsList.adapter = ContactsAdapter(contactList, this)
            contacts.close()

//            contentResolver.registerContentObserver(
//                ContactsContract.Contacts.CONTENT_URI, false, ContactsObserver(this))


            if(checkContacts){
                contentResolver.registerContentObserver(
                    ContactsContract.Contacts.CONTENT_URI, false, ContactsObserver(this)
                )
                checkContacts = false

            }
            else if(!checkContacts){
                val intent = Intent(this, ContactsDialog::class.java)
                intent.putExtra("added Name", contactsObj.name)
                intent.putExtra("added Phone", contactsObj.number)
                startActivity(intent)

//                val builder = AlertDialog.Builder(this)
//                builder.setTitle("Last added Contact")
//                builder.setMessage("Action")
//                builder.show()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}
