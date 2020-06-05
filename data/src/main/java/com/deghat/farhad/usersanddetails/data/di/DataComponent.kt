package com.deghat.farhad.usersanddetails.data.di

import com.deghat.farhad.usersanddetails.data.repository.LoginRepositoryImpl
import com.deghat.farhad.usersanddetails.data.repository.UserRepositoryImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoriesModule::class])
interface DataComponent{
    fun getLoginRepositoryImpl(): LoginRepositoryImpl
    fun getUserRepositoryImpl(): UserRepositoryImpl
}