package com.example.contactsorangeapp


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_child.view.*

class ContactsAdapter(
    items: List<Contacts>, ctx: Context
)
 : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    private var list = items
    private var context = ctx

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
        val posName = list[position].name
        val posNumber = list[position].number
        holder.name.text = "Name: $posName"
        holder.number.text = "Number: $posNumber"

    }

    override fun getItemCount(): Int {
        return list.size
    }
}