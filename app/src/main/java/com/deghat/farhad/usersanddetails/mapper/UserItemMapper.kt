package com.deghat.farhad.usersanddetails.mapper

import com.deghat.farhad.usersanddetails.domain.model.User
import com.deghat.farhad.usersanddetails.model.UserItem
import com.deghat.farhad.usersanddetails.utils.AvatarHelper
import javax.inject.Inject

class UserItemMapper @Inject constructor(
    private val avatarHelper: AvatarHelper
) {
    fun mapToPresentation(user: User): UserItem {
        val name = "${user.first_name} ${user.last_name}"
        return UserItem(
            user.id,
            name,
            user.email,
            user.avatar,
            avatarHelper.getProfilePicObservable(user.avatar)
        )
    }
}