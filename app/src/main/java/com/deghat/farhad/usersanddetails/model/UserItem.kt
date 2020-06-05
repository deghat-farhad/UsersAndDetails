package com.deghat.farhad.usersanddetails.model

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import io.reactivex.Observable

data class UserItem(
    val id: Int,
    val name: String,
    val email: String,
    val avatar: String,
    val avatarObservable: Observable<Drawable>? = null,
    var avatarDrawable: Drawable? = null
)