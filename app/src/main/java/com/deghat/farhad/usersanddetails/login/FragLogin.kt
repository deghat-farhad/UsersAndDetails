package com.deghat.farhad.usersanddetails.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.deghat.farhad.usersanddetails.R
import com.deghat.farhad.usersanddetails.di.ViewModelFactory
import kotlinx.android.synthetic.main.frag_login.view.*
import javax.inject.Inject

class FragLogin : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ViewModelLogin

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.frag_login,
            container, false
        )

        view.btnLogin.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_fragLogin_to_fragUsersList)}

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initiate(view)
        injectThisToDagger()
    }

    private fun injectThisToDagger() {

    }

    private fun initiate(view: View) {

    }
}