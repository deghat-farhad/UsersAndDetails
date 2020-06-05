package com.deghat.farhad.usersanddetails.di

import android.content.res.Resources
import com.deghat.farhad.usersanddetails.login.FragLogin
import com.deghat.farhad.usersanddetails.userDetails.FragUserDetails
import com.deghat.farhad.usersanddetails.usersList.view.FragUsersList
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ViewModelModule::class, DomainModule::class, DataModule::class])
interface ViewModelComponent {
    @Component.Builder
    interface Builder {
        fun build(): ViewModelComponent

        @BindsInstance
        fun resources(resources: Resources): Builder
    }
    fun injectFragment(fragLogin: FragLogin)
    fun injectFragment(fragUsersList: FragUsersList)
    fun injectFragment(fragUserDetails: FragUserDetails)
}