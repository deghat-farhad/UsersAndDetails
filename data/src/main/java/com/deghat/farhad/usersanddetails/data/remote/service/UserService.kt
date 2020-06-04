package com.deghat.farhad.usersanddetails.data.remote.service

import com.deghat.farhad.usersanddetails.data.entity.UsersListEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("/api/users")
    fun getUsersList(@Query("page")pageNumber: Int): Observable<UsersListEntity>
}
