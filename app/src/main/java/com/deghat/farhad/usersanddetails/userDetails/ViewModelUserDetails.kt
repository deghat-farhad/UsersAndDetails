package com.deghat.farhad.usersanddetails.userDetails

import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deghat.farhad.usersanddetails.domain.model.User
import com.deghat.farhad.usersanddetails.domain.usecase.base.DefaultObserver
import com.deghat.farhad.usersanddetails.domain.usecase.getUserDetails.GetUserDetails
import com.deghat.farhad.usersanddetails.domain.usecase.getUserDetails.GetUserDetailsParams
import com.deghat.farhad.usersanddetails.mapper.UserItemMapper
import com.deghat.farhad.usersanddetails.model.UserItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ViewModelUserDetails @Inject constructor(
    private val getUserDetails: GetUserDetails,
    private val userItemMapper: UserItemMapper
) : ViewModel() {

    val userName by lazy { MutableLiveData<String>() }
    val email by lazy { MutableLiveData<String>() }
    val isInProgress by lazy { MutableLiveData<Boolean>() }
    val profilePicture by lazy { MutableLiveData<Drawable>() }
    val isProfilePictureLoading by lazy { MutableLiveData<Boolean>() }

    private val bag = CompositeDisposable()

    fun viewIsReady(userId: Int) {
        getUserDetails(userId)
    }

    private fun getUserDetails(userId: Int) {
        val getUserDetailsObserver = object : DefaultObserver<User>() {
            override fun onNext(it: User) {
                super.onNext(it)
                showUser(userItemMapper.mapToPresentation(it))
            }

            override fun onComplete() {
                super.onComplete()
                isInProgress.value = false
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                isInProgress.value = false
            }
        }

        getUserDetailsObserver.addTo(bag)

        val params = GetUserDetailsParams(userId)

        getUserDetails.execute(getUserDetailsObserver, params)
    }

    private fun showUser(userItem: UserItem) {
        userName.value = userItem.name
        email.value = userItem.email
        isProfilePictureLoading.value = true
        userItem.avatarObservable?.subscribe {
            userItem.avatarDrawable = it
            profilePicture.value = it
            isProfilePictureLoading.value = false
        }?.addTo(bag)
    }

    override fun onCleared() {
        super.onCleared()
        bag.dispose()
    }
}