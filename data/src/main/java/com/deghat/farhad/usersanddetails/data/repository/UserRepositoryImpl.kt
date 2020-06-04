package com.deghat.farhad.usersanddetails.data.repository

import com.deghat.farhad.usersanddetails.data.mapper.UserEntityMapper
import com.deghat.farhad.usersanddetails.data.remote.Remote
import com.deghat.farhad.usersanddetails.domain.model.User
import com.deghat.farhad.usersanddetails.domain.model.UsersList
import com.deghat.farhad.usersanddetails.domain.repository.UserRepository
import io.reactivex.Observable
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remote: Remote,
    private val userEntityMapper: UserEntityMapper
):UserRepository {
    override fun getUsersList(pageNumber: Int): Observable<UsersList> {
        return remote.getUsersList(pageNumber).map { userEntityMapper.mapUsersListEntityToDomain(it) }
    }

    override fun getUser(id: Int): Observable<User> {
        return remote.getUser(id).map { userEntityMapper.mapUserDetailsEntityToDomain(it) }
    }
}