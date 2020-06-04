package com.deghat.farhad.usersanddetails.domain.usecase.getUsersList

import com.deghat.farhad.usersanddetails.domain.model.UsersList
import com.deghat.farhad.usersanddetails.domain.repository.UserRepository
import com.deghat.farhad.usersanddetails.domain.usecase.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetUsersList(
    executorThread: Scheduler,
    uiThread: Scheduler,
    private val userRepository: UserRepository
): UseCase<UsersList, GetUsersListParams>(executorThread, uiThread) {
    override fun buildUseCaseObservable(params: GetUsersListParams): Observable<UsersList> {
        return userRepository.getUsersList(params.pageNumber)
    }
}