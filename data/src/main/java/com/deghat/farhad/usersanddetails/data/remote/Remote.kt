package com.deghat.farhad.usersanddetails.data.remote

import com.deghat.farhad.usersanddetails.data.entity.LoginResponseEntity
import com.deghat.farhad.usersanddetails.data.entity.UserDetailsEntity
import com.deghat.farhad.usersanddetails.data.entity.UserEntity
import com.deghat.farhad.usersanddetails.data.entity.UsersListEntity
import com.deghat.farhad.usersanddetails.domain.model.User
import io.reactivex.Observable


class Remote(private val serviceGenerator: ServiceGenerator) {
    fun login(email: String, password: String): Observable<LoginResponseEntity> {
        return serviceGenerator.loginService().login(email, password)
            .onErrorResumeNext(CustomRxErrorHandler<LoginResponseEntity>())
    }

    fun getUsersList(pageNumber: Int): Observable<UsersListEntity> {
        return serviceGenerator.userService().getUsersList(pageNumber)
    }

    fun getUser(id: Int): Observable<UserDetailsEntity> {
        return serviceGenerator.userService().getUser(id)
    }
}