package com.deghat.farhad.usersanddetails.data.remote

import com.deghat.farhad.usersanddetails.data.remote.service.LoginService
import retrofit2.Retrofit

class ServiceGenerator(private val retrofit: Retrofit) {
    fun loginService(): LoginService = retrofit.create(LoginService::class.java)
}