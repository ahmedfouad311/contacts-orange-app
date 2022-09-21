package com.example.contactsorangeapp

import android.os.Parcel
import android.os.Parcelable

data class Contacts() : Parcelable {
    val name = ""
    val number = ""

     constructor(parcel: Parcel) : this() {
         name = parcel.readString().toString()
         number = parcel.readString().toString()
     }

     override fun writeToParcel(parcel: Parcel, flags: Int) {
         parcel.writeString(name)
         parcel.writeString(number)
     }

     override fun describeContents(): Int {
         return 0
     }

     companion object CREATOR : Parcelable.Creator<Contacts> {
         override fun createFromParcel(parcel: Parcel): Contacts {
             return Contacts(parcel)
         }

         override fun newArray(size: Int): Array<Contacts?> {
             return arrayOfNulls(size)
         }
     }
 }