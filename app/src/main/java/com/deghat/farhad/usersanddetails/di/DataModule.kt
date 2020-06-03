package com.deghat.farhad.usersanddetails.di

import com.deghat.farhad.usersanddetails.data.di.DaggerDataComponent
import com.deghat.farhad.usersanddetails.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun loginRepository(): LoginRepository =
        DaggerDataComponent.create().getLoginRepositoryImpl()
}