package com.deghat.farhad.usersanddetails.userDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.deghat.farhad.usersanddetails.R
import com.deghat.farhad.usersanddetails.di.DaggerViewModelComponent
import com.deghat.farhad.usersanddetails.di.ViewModelFactory
import javax.inject.Inject

class FragUserDetails : Fragment() {

    val args: FragUserDetailsArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ViewModelUserDetails

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val userId = args.selectedUserId

        return inflater.inflate(
            R.layout.frag_user_details,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        injectThisToDagger()
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ViewModelUserDetails::class.java)
        initiate(view)
        setObservers()
    }

    private fun setObservers() {

    }

    private fun initiate(view: View) {

    }

    private fun injectThisToDagger() {
        DaggerViewModelComponent
            .create()
            .injectFragment(this)
    }
}
