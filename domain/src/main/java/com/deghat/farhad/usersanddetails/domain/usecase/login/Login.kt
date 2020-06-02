package com.deghat.farhad.usersanddetails.domain.usecase.login

import com.deghat.farhad.usersanddetails.domain.usecase.base.UseCase
import com.deghat.farhad.usersanddetails.domain.repository.LoginRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class Login(
    executorThread: Scheduler,
    uiThread: Scheduler,
    private val loginRepository: LoginRepository
): UseCase<String, LoginParams>(executorThread, uiThread) {
    override fun buildUseCaseObservable(params: LoginParams): Observable<String> {
        return loginRepository.login(params.email, params.password)
    }
}