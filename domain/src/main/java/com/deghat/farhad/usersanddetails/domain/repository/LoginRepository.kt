package com.deghat.farhad.usersanddetails.domain.repository

import com.deghat.farhad.usersanddetails.domain.model.LoginResponse
import io.reactivex.Observable

interface LoginRepository {
    fun login(email: String, password: String): Observable<LoginResponse>
}