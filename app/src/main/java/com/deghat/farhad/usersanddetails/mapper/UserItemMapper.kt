package com.deghat.farhad.usersanddetails.mapper

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.deghat.farhad.usersanddetails.domain.model.User
import com.deghat.farhad.usersanddetails.model.UserItem
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import javax.inject.Inject

class UserItemMapper @Inject constructor(){
    fun mapToPresentation(user: User): UserItem {
        val name = "${user.first_name} ${user.last_name}"
        val profilePicObservable = getProfilePicObservable(user.avatar)
        val userItem = UserItem(user.id, name, user.email, user.avatar, profilePicObservable)
        profilePicObservable?.doOnNext { userItem.avatarBitmap = it }
        return userItem
    }

    private fun getProfilePicObservable(profilePictureUrl: String?) : Observable<Bitmap>? {
        profilePictureUrl?.let { notNullProfilePictureUrl ->
            return Observable.create<Bitmap>{ emitter ->
                val target = object : com.squareup.picasso.Target {
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

                    override fun onBitmapFailed(e: Exception, errorDrawable: Drawable?) {
                        emitter.onError(e)
                    }

                    override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom?) {
                        emitter.onNext(bitmap)
                        emitter.onComplete()
                    }
                }
                Picasso.get().load(notNullProfilePictureUrl)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(target)
            }
        }
        return null
    }
}