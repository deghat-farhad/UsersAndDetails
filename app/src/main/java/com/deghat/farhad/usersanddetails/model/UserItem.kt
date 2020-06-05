package com.deghat.farhad.usersanddetails.model

import android.graphics.Bitmap
import io.reactivex.Observable

data class UserItem(
    val id: Int,
    val name: String,
    val email: String,
    val avatar: String,
    val avatarObservable: Observable<Bitmap>? = null,
    var avatarBitmap: Bitmap? = null
)