package com.deghat.farhad.usersanddetails.login

import androidx.lifecycle.ViewModel
import com.deghat.farhad.usersanddetails.domain.usecase.login.Login
import javax.inject.Inject

class ViewModelLogin @Inject constructor(
    private val login: Login
) : ViewModel() {
}