package com.deghat.farhad.usersanddetails.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deghat.farhad.usersanddetails.domain.model.LoginResponse
import com.deghat.farhad.usersanddetails.domain.usecase.base.DefaultObserver
import com.deghat.farhad.usersanddetails.domain.usecase.login.Login
import com.deghat.farhad.usersanddetails.domain.usecase.login.LoginParams
import com.deghat.farhad.usersanddetails.utils.SingleLiveEvent
import javax.inject.Inject

class ViewModelLogin @Inject constructor(
    private val login: Login
) : ViewModel() {
    val goToUsersList: SingleLiveEvent<Unit> by lazy { SingleLiveEvent<Unit>() }
    val loginFailed: SingleLiveEvent<String> by lazy { SingleLiveEvent<String>() }
    val loginIsInProgress: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    fun loginBtnTaped(email: String, password: String) {
        login(email, password)
    }

    private fun login(email: String, password: String){
        val loginObserver = object : DefaultObserver<LoginResponse>(){
            override fun onNext(it: LoginResponse) {
                super.onNext(it)
                goToUsersList.call()
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                loginFailed.value = e.message
                loginIsInProgress.value = false
            }

            override fun onComplete() {
                super.onComplete()
                loginIsInProgress.value = false
            }
        }

        val loginParams = LoginParams(email, password)
        loginIsInProgress.value = true
        login.execute(loginObserver, loginParams)
    }
}