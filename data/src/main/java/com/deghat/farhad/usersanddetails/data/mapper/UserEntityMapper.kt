package com.deghat.farhad.usersanddetails.data.mapper

import com.deghat.farhad.usersanddetails.data.entity.UserDetailsEntity
import com.deghat.farhad.usersanddetails.data.entity.UserEntity
import com.deghat.farhad.usersanddetails.data.entity.UsersListEntity
import com.deghat.farhad.usersanddetails.domain.model.User
import com.deghat.farhad.usersanddetails.domain.model.UsersList
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
        UsersList(usersListEntity.total_pages == usersListEntity.page,
            usersListEntity.data.map { mapUserEntityToDomain(it) })

    fun mapUserDetailsEntityToDomain(userDetailsEntity: UserDetailsEntity) =
        mapUserEntityToDomain(userDetailsEntity.data)
}