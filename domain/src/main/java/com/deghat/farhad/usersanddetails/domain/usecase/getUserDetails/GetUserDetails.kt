package com.deghat.farhad.usersanddetails.domain.usecase.getUserDetails

import com.deghat.farhad.usersanddetails.domain.model.User
import com.deghat.farhad.usersanddetails.domain.repository.UserRepository
import com.deghat.farhad.usersanddetails.domain.usecase.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetUserDetails(
    executorThread: Scheduler,
    uiThread: Scheduler,
    private val userRepository: UserRepository
) : UseCase<User, GetUserDetailsParams>(executorThread, uiThread){
    override fun buildUseCaseObservable(params: GetUserDetailsParams): Observable<User> {
        return userRepository.getUser(params.id)
    }
}