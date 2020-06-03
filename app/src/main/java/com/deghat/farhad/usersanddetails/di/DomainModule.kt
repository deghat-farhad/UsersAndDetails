package com.deghat.farhad.usersanddetails.di

import com.deghat.farhad.usersanddetails.domain.repository.LoginRepository
import com.deghat.farhad.usersanddetails.domain.usecase.login.Login
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class DomainModule {

    @Provides
    fun login(
        loginRepository: LoginRepository,
        @Named("ioScheduler") ioScheduler: Scheduler,
        @Named("mainThreadScheduler") mainThreadScheduler: Scheduler
    ): Login {
        return Login(ioScheduler, mainThreadScheduler, loginRepository)
    }

    @Provides
    @Named("ioScheduler")
    fun ioScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Named("mainThreadScheduler")
    fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()
}