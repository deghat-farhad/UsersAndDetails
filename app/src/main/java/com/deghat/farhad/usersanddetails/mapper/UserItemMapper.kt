package com.deghat.farhad.usersanddetails.mapper

import com.deghat.farhad.usersanddetails.domain.model.User
import com.deghat.farhad.usersanddetails.model.UserItem
import javax.inject.Inject

class UserItemMapper @Inject constructor(){
    fun mapToPresentation(user: User): UserItem {
        val name = "${user.first_name} ${user.last_name}"
        return UserItem(user.id, name, user.email, user.avatar)
    }
}