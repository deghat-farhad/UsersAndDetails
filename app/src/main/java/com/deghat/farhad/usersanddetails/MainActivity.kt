package com.deghat.farhad.usersanddetails


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment


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

        graph.startDestination = R.id.fragUsersList

        navController.graph = graph
    }
}
