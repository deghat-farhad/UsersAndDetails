package com.deghat.farhad.usersanddetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

class FragUserDetails : Fragment() {

    val args:FragUserDetailsArgs by navArgs()

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
}
