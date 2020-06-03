package com.deghat.farhad.usersanddetails.data.remote

import com.deghat.farhad.usersanddetails.data.entity.LoginResponseEntity
import io.reactivex.Observable


class Remote(private  val serviceGenerator: ServiceGenerator) {
    fun login(email: String, password: String): Observable<LoginResponseEntity> {
        return serviceGenerator.loginService().login(email, password)
            .onErrorResumeNext(CustomRxErrorHandler<LoginResponseEntity>())
    }
}