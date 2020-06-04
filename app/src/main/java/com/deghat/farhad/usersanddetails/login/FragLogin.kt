package com.deghat.farhad.usersanddetails.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.deghat.farhad.usersanddetails.R
import com.deghat.farhad.usersanddetails.di.DaggerViewModelComponent
import com.deghat.farhad.usersanddetails.di.ViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.frag_login.view.*
import javax.inject.Inject

class FragLogin : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ViewModelLogin

    private lateinit var btnLogin: Button
    private lateinit var txtInputEdtTxtEmail: TextInputEditText
    private lateinit var txtInputEdtTxtPassword: TextInputEditText
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.frag_login,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initiate(view)
        injectThisToDagger()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ViewModelLogin::class.java)
        setObservers()
        setActions()
    }

    private fun setActions() {
        btnLogin.setOnClickListener {
            viewModel.loginBtnTaped(
                txtInputEdtTxtEmail.text.toString(),
                txtInputEdtTxtPassword.text.toString()
            )
        }
    }

    private fun setObservers() {
        viewModel.goToUsersList.observe(
            this,
            Observer {
                navController.navigate(R.id.action_fragLogin_to_fragUsersList)
            })

        viewModel.loginFailed.observe(
            this,
            Observer {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        )

        viewModel.loginIsInProgress.observe(
            this,
            Observer {
                waitForLoggingIn(it)
            }
        )
    }

    private fun injectThisToDagger() {
        DaggerViewModelComponent
            .create()
            .injectFragment(this)
    }

    private fun initiate(view: View) {
        btnLogin = view.btnLogin
        txtInputEdtTxtEmail = view.txtInputEdtTxtEmail
        txtInputEdtTxtPassword = view.txtInputEdtTxtPassword
        navController = Navigation.findNavController(view)
    }

    private fun waitForLoggingIn(flag: Boolean) {
        if (flag) {
            txtInputEdtTxtEmail.isEnabled = false
            txtInputEdtTxtPassword.isEnabled = false
            btnLogin.text = getString(R.string.Logging_in)
            btnLogin.isEnabled = false
        } else {
            txtInputEdtTxtEmail.isEnabled = true
            txtInputEdtTxtPassword.isEnabled = true
            btnLogin.text = getString(R.string.btnLogin)
            btnLogin.isEnabled = true
        }
    }
}