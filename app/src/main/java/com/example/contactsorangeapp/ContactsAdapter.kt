package com.example.contactsorangeapp


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_child.view.*

class ContactsAdapter( // contains all the logic needed
    private val contacts: MutableList<Contacts>,
) : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.tvContactName!!
        val number = itemView.tvContactNumber!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        return ContactsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.contact_child,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val curContact = contacts[position]
        holder.name.text = curContact.toString()
        holder.number.text = curContact.toString()

    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}