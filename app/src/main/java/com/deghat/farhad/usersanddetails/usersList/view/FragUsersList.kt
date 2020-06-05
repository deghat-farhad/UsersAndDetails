package com.deghat.farhad.usersanddetails.usersList.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deghat.farhad.usersanddetails.R
import com.deghat.farhad.usersanddetails.di.DaggerViewModelComponent
import com.deghat.farhad.usersanddetails.di.ViewModelFactory
import com.deghat.farhad.usersanddetails.usersList.ViewModelUsersList
import kotlinx.android.synthetic.main.frag_users_list.view.*
import javax.inject.Inject

class FragUsersList : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ViewModelUsersList

    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var navController: NavController


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

        injectThisToDagger()
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ViewModelUsersList::class.java)
        initiate(view)
        setObservers()
        setupScrollListener()
        viewModel.viewIsReady()
    }

    private fun setObservers() {
        viewModel.newItemsAddedToUsers.observe(this, Observer {
            usersAdapter.notifyItemRangeInserted(viewModel.users.size - it, it)
        })

        viewModel.isAllItemsLoaded.observe(this, Observer {
            if (it)
                hideLoading()
            else
                showLoading()
        })

        viewModel.isFailedToLoad.observe(this, Observer {
            if (it)
                showRetry()
            else
                hideRetry()
        })

        viewModel.selectedUserId.observe(this, Observer {
            val action = FragUsersListDirections.actionFragUsersListToFragUserDetails(it)
            navController.navigate(action)
        })
    }

    private fun injectThisToDagger() {
        DaggerViewModelComponent
            .create()
            .injectFragment(this)
    }

    private fun initiate(view: View) {
        recyclerViewUsers = view.RecViwUsers
        usersAdapter = UsersAdapter(viewModel.users, viewModel::retry, viewModel::userItemClick, resources)
        initiateUsersRecyclerView()
        navController = Navigation.findNavController(view)
    }

    private fun initiateUsersRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerViewUsers.apply {
            layoutManager = linearLayoutManager
            adapter = usersAdapter
        }
    }

    private fun setupScrollListener() {
        val layoutManager = recyclerViewUsers.layoutManager as LinearLayoutManager
        recyclerViewUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                viewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
            }
        })
    }

    private fun hideLoading() {
        recyclerViewUsers.post {
            usersAdapter.hideLoading()
        }
    }

    private fun showLoading() {
        recyclerViewUsers.post {
            usersAdapter.showLoading()
        }
    }

    private fun hideRetry() {
        recyclerViewUsers.post {
            usersAdapter.hideRetry()
        }
    }

    private fun showRetry() {
        recyclerViewUsers.post {
            usersAdapter.showRetry()
        }
    }
}
