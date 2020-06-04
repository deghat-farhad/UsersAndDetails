package com.deghat.farhad.usersanddetails.usersList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deghat.farhad.usersanddetails.domain.model.UsersList
import com.deghat.farhad.usersanddetails.domain.usecase.base.DefaultObserver
import com.deghat.farhad.usersanddetails.domain.usecase.getUsersList.GetUsersList
import com.deghat.farhad.usersanddetails.domain.usecase.getUsersList.GetUsersListParams
import com.deghat.farhad.usersanddetails.mapper.UserItemMapper
import com.deghat.farhad.usersanddetails.model.UserItem
import com.deghat.farhad.usersanddetails.utils.SingleLiveEvent
import java.util.*
import javax.inject.Inject

class ViewModelUsersList @Inject constructor(
    private val getUsersList: GetUsersList,
    val userItemMapper: UserItemMapper
) : ViewModel() {

    val users: LinkedList<UserItem?> = LinkedList()

    private var isLoading = false
    private var isLastPage = true
    private var pageNumber = 1

    val newItemsAddedToUsers by lazy { SingleLiveEvent<Int>() }
    val isAllItemsLoaded by lazy { MutableLiveData<Boolean>() }
    val isFailedToLoad by lazy { MutableLiveData<Boolean>() }
    val selectedUserId by lazy {SingleLiveEvent<Int>()}

    companion object {
        private const val VISIBLE_THRESHOLD = 2
    }

    fun userItemClick(userId: Int){
        selectedUserId.value = userId
    }

    fun retry() {
        isFailedToLoad.value = false
        getUsers()
    }

    fun viewIsReady() {
        loadMore()
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount && !isLoading) {
            if (!isLastPage) {
                loadMore()
            }
        }
    }

    private fun loadMore() {
        if (isFailedToLoad.value == null || isFailedToLoad.value == false)
            getUsers()
    }

    private fun getUsers() {
        val getUsersListObserver = object : DefaultObserver<UsersList>() {
            override fun onNext(it: UsersList) {
                super.onNext(it)
                users.addAll(it.usersList.map { userItemMapper.mapToPresentation(it) })
                pageNumber++
                newItemsAddedToUsers.value = it.usersList.size
                isLastPage = it.isLastPage
                if (it.isLastPage)
                    isAllItemsLoaded.value = true
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                isFailedToLoad.value = true
            }
        }
        val getUsersListParams = GetUsersListParams(pageNumber)
        getUsersList.execute(getUsersListObserver, getUsersListParams)
    }
}