package com.deghat.farhad.usersanddetails.data.remote.service

import com.deghat.farhad.usersanddetails.data.entity.LoginResponseEntity
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @POST("/api/login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<LoginResponseEntity>
}