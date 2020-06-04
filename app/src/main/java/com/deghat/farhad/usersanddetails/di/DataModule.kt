package com.deghat.farhad.usersanddetails.di

import com.deghat.farhad.usersanddetails.data.di.DaggerDataComponent
import com.deghat.farhad.usersanddetails.domain.repository.LoginRepository
import com.deghat.farhad.usersanddetails.domain.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun userRepository(): UserRepository =
        DaggerDataComponent.create().getUserRepositoryImpl()
    @Provides
    fun loginRepository(): LoginRepository =
        DaggerDataComponent.create().getLoginRepositoryImpl()
}