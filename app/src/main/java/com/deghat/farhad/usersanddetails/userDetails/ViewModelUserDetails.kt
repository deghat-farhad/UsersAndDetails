package com.deghat.farhad.usersanddetails.userDetails

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deghat.farhad.usersanddetails.domain.model.User
import com.deghat.farhad.usersanddetails.domain.usecase.base.DefaultObserver
import com.deghat.farhad.usersanddetails.domain.usecase.getUserDetails.GetUserDetails
import com.deghat.farhad.usersanddetails.domain.usecase.getUserDetails.GetUserDetailsParams
import com.deghat.farhad.usersanddetails.mapper.UserItemMapper
import com.deghat.farhad.usersanddetails.model.UserItem
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ViewModelUserDetails @Inject constructor(
    private val getUserDetails: GetUserDetails,
    private val userItemMapper: UserItemMapper
) : ViewModel() {

    val userName by lazy { MutableLiveData<String>() }
    val email by lazy { MutableLiveData<String>() }
    val isInProgress by lazy { MutableLiveData<Boolean>() }
    val profilePicture by lazy { MutableLiveData<Bitmap>() }
    val isProfilePictureLoading by lazy { MutableLiveData<Boolean>() }
    val isProfilePictureFailedToLoad by lazy { MutableLiveData<Boolean>() }

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

        val params = GetUserDetailsParams(userId)

        getUserDetails.execute(getUserDetailsObserver, params)
    }

    private fun showUser(userItem: UserItem) {
        userName.value = userItem.name
        email.value = userItem.email
        setLiveProfilePicture(userItem.avatar)
    }

    private fun setLiveProfilePicture(profilePictureUrl: String?) {
        profilePictureUrl?.let { notNullProfilePictureUrl ->
            val target = object : com.squareup.picasso.Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    isProfilePictureLoading.value = true
                    isProfilePictureFailedToLoad.value = false
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    isProfilePictureLoading.value = false
                    isProfilePictureFailedToLoad.value = true
                }

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    bitmap?.let { profilePicture.value = it }
                    isProfilePictureLoading.value = false
                    isProfilePictureFailedToLoad.value = false
                }
            }
            Picasso.get().load(notNullProfilePictureUrl)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(target)
        }
    }
}