package com.deghat.farhad.usersanddetails.domain.model

data class UsersList(
    val isLastPage: Boolean,
    val usersList: List<User>
)