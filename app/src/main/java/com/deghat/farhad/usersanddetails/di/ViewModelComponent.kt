package com.deghat.farhad.usersanddetails.di

import com.deghat.farhad.usersanddetails.login.FragLogin
import dagger.Component

@Component(modules = [ViewModelModule::class, DomainModule::class, DataModule::class])
interface ViewModelComponent {
    fun injectFragment(fragLogin: FragLogin)
}