package com.deghat.farhad.usersanddetails.domain.repository

import com.deghat.farhad.usersanddetails.domain.model.User
import io.reactivex.Observable

interface UserRepository {
    fun getUsersList(pageNumber: Int): Observable<List<User>>
}