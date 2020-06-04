package com.deghat.farhad.usersanddetails.usersList.view.items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deghat.farhad.usersanddetails.R
import com.deghat.farhad.usersanddetails.model.UserItem

class UserHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val txtViwUserName = itemView.findViewById<TextView>(R.id.txtViwDoctorName)
    private val txtViwUserEmail = itemView.findViewById<TextView>(R.id.txtViwAddress)

    fun bind(doctorItem: UserItem) {
        txtViwUserName.text = doctorItem.name
        txtViwUserEmail.text = doctorItem.email
    }

    companion object {
        fun create(parent: ViewGroup): UserHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_item, parent, false)
            return UserHolder(view)
        }
    }
}
