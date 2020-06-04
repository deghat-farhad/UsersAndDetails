package com.deghat.farhad.usersanddetails.data.mapper

import com.deghat.farhad.usersanddetails.data.entity.UserEntity
import com.deghat.farhad.usersanddetails.data.entity.UsersListEntity
import com.deghat.farhad.usersanddetails.domain.model.User
import javax.inject.Inject

class UserEntityMapper @Inject constructor() {
    private fun mapUserEntityToDomain(userEntity: UserEntity) = User(
        userEntity.id,
        userEntity.email,
        userEntity.first_name,
        userEntity.last_name,
        userEntity.avatar
    )

    fun mapUsersListEntityToDomain(usersListEntity: UsersListEntity) =
        usersListEntity.data.map { mapUserEntityToDomain(it) }
}