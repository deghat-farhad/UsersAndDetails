package com.deghat.farhad.usersanddetails.domain.usecase.getUsersList

import com.deghat.farhad.usersanddetails.domain.model.User
import com.deghat.farhad.usersanddetails.domain.repository.UserRepository
import com.deghat.farhad.usersanddetails.domain.usecase.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetUsersList(
    executorThread: Scheduler,
    uiThread: Scheduler,
    private val userRepository: UserRepository
): UseCase<List<User>, GetUsersListParams>(executorThread, uiThread) {
    override fun buildUseCaseObservable(params: GetUsersListParams): Observable<List<User>> {
        return userRepository.getUsersList(params.pageNumber)
    }
}