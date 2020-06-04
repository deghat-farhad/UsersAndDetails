package com.deghat.farhad.usersanddetails.data.entity

data class UsersListEntity (
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<UserEntity>
)