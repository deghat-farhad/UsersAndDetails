package com.deghat.farhad.usersanddetails.usersList.view.holders

import android.content.res.Resources
import android.graphics.drawable.Drawable
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
    private val imgViwProfile = itemView.imgViwProfile
    private val root = itemView.rootView
    private var userId = -1

    fun bind(userItem: UserItem, onClickFunction: KFunction1<Int, Unit>, resources: Resources) {
        txtViwUserName.text = userItem.name
        txtViwUserEmail.text = userItem.email
        userId = userItem.id
        root.setOnClickListener{onClickFunction(userId)}
        imgViwProfile.setImageDrawable(null)
        if(userItem.avatarDrawable == null)
            userItem.avatarObservable?.subscribe {
                userItem.avatarDrawable = it
                imgViwProfile.setImageDrawable(it)
            }
        else
        userItem.avatarDrawable?.let {
            imgViwProfile.setImageDrawable(it)
        }
    }

    companion object {
        fun create(parent: ViewGroup): UserHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_item, parent, false)
            return UserHolder(view)
        }
    }
}
