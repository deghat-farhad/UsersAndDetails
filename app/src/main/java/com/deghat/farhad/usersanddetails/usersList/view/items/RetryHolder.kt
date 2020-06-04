package com.deghat.farhad.usersanddetails.usersList.view.items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.deghat.farhad.usersanddetails.R

class RetryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val btnRetry: Button = itemView.findViewById(R.id.btnRetry)
    fun bind(retryFunction: () -> Unit) {
        btnRetry.setOnClickListener {
            retryFunction()
        }
    }

    companion object {
        fun create(parent: ViewGroup): RetryHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.retry_item, parent, false)
            return RetryHolder(view)
        }
    }
}