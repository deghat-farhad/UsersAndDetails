package com.deghat.farhad.usersanddetails.data.remote

import com.deghat.farhad.usersanddetails.data.remote.service.LoginService
import com.deghat.farhad.usersanddetails.data.remote.service.UserService
import retrofit2.Retrofit

class ServiceGenerator(private val retrofit: Retrofit) {
    fun loginService(): LoginService = retrofit.create(LoginService::class.java)
    fun userService(): UserService = retrofit.create(UserService::class.java)
}