package com.deghat.farhad.usersanddetails.di

import com.deghat.farhad.usersanddetails.login.FragLogin
import com.deghat.farhad.usersanddetails.userDetails.FragUserDetails
import com.deghat.farhad.usersanddetails.usersList.view.FragUsersList
import dagger.Component

@Component(modules = [ViewModelModule::class, DomainModule::class, DataModule::class])
interface ViewModelComponent {
    fun injectFragment(fragLogin: FragLogin)
    fun injectFragment(fragUsersList: FragUsersList)
    fun injectFragment(fragUserDetails: FragUserDetails)
}