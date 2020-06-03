package com.deghat.farhad.usersanddetails.di

import androidx.lifecycle.ViewModel
import com.deghat.farhad.usersanddetails.login.ViewModelLogin
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
    fun bindUpdateProfileViewModel(viewModelLogin: ViewModelLogin): ViewModel
}