package com.deghat.farhad.usersanddetails.domain.repository

import com.deghat.farhad.usersanddetails.domain.model.User
import com.deghat.farhad.usersanddetails.domain.model.UsersList
import io.reactivex.Observable

interface UserRepository {
    fun getUsersList(pageNumber: Int): Observable<UsersList>
    fun getUser(id: Int): Observable<User>
}