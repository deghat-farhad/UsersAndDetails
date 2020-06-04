package com.deghat.farhad.usersanddetails.usersList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.deghat.farhad.usersanddetails.R
import com.deghat.farhad.usersanddetails.di.DaggerViewModelComponent
import com.deghat.farhad.usersanddetails.di.ViewModelFactory
import com.deghat.farhad.usersanddetails.login.ViewModelLogin
import javax.inject.Inject

class FragUsersList : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ViewModelUsersList

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.frag_users_list,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initiate(view)
        injectThisToDagger()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ViewModelUsersList::class.java)
        setObservers()
        setActions()
    }

    private fun setActions() {
    }

    private fun setObservers() {
    }

    private fun injectThisToDagger() {
        DaggerViewModelComponent
            .create()
            .injectFragment(this)
    }

    private fun initiate(view: View) {

    }
}
