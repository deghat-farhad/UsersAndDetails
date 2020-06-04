package com.deghat.farhad.usersanddetails.usersList.view.items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deghat.farhad.usersanddetails.R
import com.deghat.farhad.usersanddetails.model.UserItem
import kotlinx.android.synthetic.main.user_item.view.*
import kotlin.reflect.KFunction1

class UserHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val txtViwUserName = itemView.txtViwUserName
    private val txtViwUserEmail = itemView.txtViwEmail
    private val root = itemView.rootView
    private var userId = -1

    fun bind(userItem: UserItem, onClickFunction: KFunction1<Int, Unit>) {
        txtViwUserName.text = userItem.name
        txtViwUserEmail.text = userItem.email
        userId = userItem.id
        root.setOnClickListener{onClickFunction(userId)}
    }

    companion object {
        fun create(parent: ViewGroup): UserHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_item, parent, false)
            return UserHolder(view)
        }
    }
}
