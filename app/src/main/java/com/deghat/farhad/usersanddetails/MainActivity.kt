package com.deghat.farhad.usersanddetails


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.deghat.farhad.usersanddetails.data.di.DaggerDataComponent
import com.deghat.farhad.usersanddetails.domain.model.User
import com.deghat.farhad.usersanddetails.domain.usecase.base.DefaultObserver
import com.deghat.farhad.usersanddetails.domain.usecase.getUserDetails.GetUserDetails
import com.deghat.farhad.usersanddetails.domain.usecase.getUserDetails.GetUserDetailsParams
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNavigation()
    }

    private fun setNavigation() {
        val navHost = supportFragmentManager.findFragmentById(R.id.navHostFragment)
                as NavHostFragment? ?: return
        val navController = navHost.navController


        val inflater = navController.navInflater
        val graph = inflater.inflate(R.navigation.navigation)

        graph.startDestination = R.id.fragLogin

        navController.graph = graph
    }
}
