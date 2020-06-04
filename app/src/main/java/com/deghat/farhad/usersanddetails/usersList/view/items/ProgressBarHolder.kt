package com.deghat.farhad.usersanddetails.usersList.view.items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deghat.farhad.usersanddetails.R

class ProgressBarHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun create(parent: ViewGroup): ProgressBarHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.progress_bar_item, parent, false)
            return ProgressBarHolder(view)
        }
    }
}