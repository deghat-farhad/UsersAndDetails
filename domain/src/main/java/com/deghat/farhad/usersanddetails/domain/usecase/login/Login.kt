package com.deghat.farhad.usersanddetails.domain.usecase.login

import com.deghat.farhad.usersanddetails.domain.model.LoginResponse
import com.deghat.farhad.usersanddetails.domain.usecase.base.UseCase
import com.deghat.farhad.usersanddetails.domain.repository.LoginRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class Login(
    executorThread: Scheduler,
    uiThread: Scheduler,
    private val loginRepository: LoginRepository
): UseCase<LoginResponse, LoginParams>(executorThread, uiThread) {
    override fun buildUseCaseObservable(params: LoginParams): Observable<LoginResponse> {
        return loginRepository.login(params.email, params.password)
    }
}