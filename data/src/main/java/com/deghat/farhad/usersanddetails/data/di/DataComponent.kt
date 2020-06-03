package com.deghat.farhad.usersanddetails.data.di

import com.deghat.farhad.usersanddetails.data.repository.LoginRepositoryImpl
import dagger.Component

@Component(modules = [RepositoriesModule::class])
interface DataComponent{
    fun getLoginRepositoryImpl(): LoginRepositoryImpl
}