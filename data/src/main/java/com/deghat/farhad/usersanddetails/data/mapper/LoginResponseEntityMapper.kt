package com.deghat.farhad.usersanddetails.data.mapper

import com.deghat.farhad.usersanddetails.data.entity.LoginResponseEntity
import com.deghat.farhad.usersanddetails.domain.model.LoginResponse

class LoginResponseEntityMapper {
    fun mapToData(loginResponseEntity: LoginResponseEntity) =
        LoginResponse(loginResponseEntity.token)
}