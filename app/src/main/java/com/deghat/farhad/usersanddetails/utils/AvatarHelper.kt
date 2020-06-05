package com.deghat.farhad.usersanddetails.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import javax.inject.Inject
import kotlin.math.min

class AvatarHelper @Inject constructor(private val resources: Resources){
    fun getProfilePicObservable(profilePictureUrl: String?) : Observable<Drawable>? {
        profilePictureUrl?.let { notNullProfilePictureUrl ->
            return Observable.create<Drawable>{ emitter ->
                val target = object : com.squareup.picasso.Target {
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

                    override fun onBitmapFailed(e: Exception, errorDrawable: Drawable?) {
                        emitter.onError(e)
                    }

                    override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom?) {
                        emitter.onNext(bitmapToRoundDrawable(bitmap))
                        emitter.onComplete()
                    }
                }
                Picasso.get().load(notNullProfilePictureUrl)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(target)
            }
        }
        return null
    }

    private fun bitmapToRoundDrawable(
        bitmap: Bitmap
    ): Drawable {
        val imageDrawable = RoundedBitmapDrawableFactory.create(resources, bitmap)
        imageDrawable.isCircular = true
        imageDrawable.cornerRadius = min(bitmap.width, bitmap.height) / 2.0f

        return imageDrawable
    }
}