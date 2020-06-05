package com.deghat.farhad.usersanddetails.userDetails

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.deghat.farhad.usersanddetails.R
import com.deghat.farhad.usersanddetails.di.DaggerViewModelComponent
import com.deghat.farhad.usersanddetails.di.ViewModelFactory
import kotlinx.android.synthetic.main.frag_user_details.view.*
import javax.inject.Inject
import kotlin.math.min

class FragUserDetails : Fragment() {

    private val args: FragUserDetailsArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ViewModelUserDetails

    private lateinit var txtViwUserNameDetails: TextView
    private lateinit var txtViwEmailDetails: TextView
    private lateinit var progressBarDetails: ProgressBar
    private lateinit var imgViwProfileDetails: ImageView
    private lateinit var progressBarProfilePic: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.frag_user_details,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        injectThisToDagger()
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ViewModelUserDetails::class.java)
        initiate(view)
        setObservers()
        viewModel.viewIsReady(args.selectedUserId)
    }

    private fun setObservers() {
        viewModel.userName.observe(this, Observer { txtViwUserNameDetails.text = it })
        viewModel.email.observe(this, Observer { txtViwEmailDetails.text = it })
        viewModel.isInProgress.observe(this, Observer {
            if (it)
                showProgress()
            else
                hideProgress()
        })
        viewModel.profilePicture.observe(
            this,
            Observer { imgViwProfileDetails.setImageDrawable(bitmapToRoundDrawable(it)) })
        viewModel.isProfilePictureLoading.observe(this, Observer {
            if (it)
                progressBarProfilePic.visibility = View.VISIBLE
            else
                progressBarProfilePic.visibility = View.GONE
        })

        viewModel.isProfilePictureFailedToLoad.observe(this, Observer {
            if(it){
                context?.let { context ->
                    val defaultProfilePic =
                        ContextCompat.getDrawable(context, R.drawable.ic_profile)
                    imgViwProfileDetails.setImageDrawable(defaultProfilePic)
                }
            }
        })
    }

    private fun hideProgress() {
        txtViwUserNameDetails.visibility = View.VISIBLE
        txtViwEmailDetails.visibility = View.VISIBLE
        imgViwProfileDetails.visibility = View.VISIBLE
        progressBarDetails.visibility = View.GONE
    }

    private fun showProgress() {
        txtViwUserNameDetails.visibility = View.GONE
        txtViwEmailDetails.visibility = View.GONE
        imgViwProfileDetails.visibility = View.GONE
        progressBarDetails.visibility = View.VISIBLE
    }

    private fun initiate(view: View) {
        txtViwUserNameDetails = view.txtViwUserNameDetails
        txtViwEmailDetails = view.txtViwEmailDetails
        progressBarDetails = view.progressBarDetails
        imgViwProfileDetails = view.imgViwProfileDetails
        progressBarProfilePic = view.progressBarProfilePic
    }

    private fun injectThisToDagger() {
        DaggerViewModelComponent
            .create()
            .injectFragment(this)
    }

    private fun bitmapToRoundDrawable(bitmap: Bitmap): Drawable {
        val imageDrawable = RoundedBitmapDrawableFactory.create(resources, bitmap)
        imageDrawable.isCircular = true
        imageDrawable.cornerRadius = min(bitmap.width, bitmap.height) / 2.0f

        return imageDrawable
    }
}
