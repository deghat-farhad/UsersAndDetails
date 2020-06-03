package com.deghat.farhad.usersanddetails.data.repository

import com.deghat.farhad.usersanddetails.data.mapper.LoginResponseEntityMapper
import com.deghat.farhad.usersanddetails.data.remote.Remote
import com.deghat.farhad.usersanddetails.domain.model.LoginResponse
import com.deghat.farhad.usersanddetails.domain.repository.LoginRepository
import io.reactivex.Observable

class LoginRepositoryImpl(
    private val remote: Remote,
    private val loginResponseEntityMapper: LoginResponseEntityMapper
) : LoginRepository {
    override fun login(email: String, password: String): Observable<LoginResponse> {
        return remote.login(email, password).map { loginResponseEntityMapper.mapToData(it) }
    }
}