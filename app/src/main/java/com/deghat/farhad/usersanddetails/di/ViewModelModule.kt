package com.deghat.farhad.usersanddetails.di

import androidx.lifecycle.ViewModel
import com.deghat.farhad.usersanddetails.login.ViewModelLogin
import com.deghat.farhad.usersanddetails.userDetails.ViewModelUserDetails
import com.deghat.farhad.usersanddetails.usersList.ViewModelUsersList
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelLogin::class)
    fun bindLoginViewModel(viewModelLogin: ViewModelLogin): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelUsersList::class)
    fun bindUsersListViewModel(viewModelUsersList: ViewModelUsersList): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelUserDetails::class)
    fun bindUsersDetailsViewModel(viewModelUserDetails: ViewModelUserDetails): ViewModel
}