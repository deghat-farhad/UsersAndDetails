package com.deghat.farhad.usersanddetails.usersList.view

import android.content.res.Resources
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deghat.farhad.usersanddetails.model.UserItem
import com.deghat.farhad.usersanddetails.usersList.view.holders.ProgressBarHolder
import com.deghat.farhad.usersanddetails.usersList.view.holders.RetryHolder
import com.deghat.farhad.usersanddetails.usersList.view.holders.UserHolder
import kotlin.reflect.KFunction1

class UsersAdapter(
    private val Users: List<UserItem?>,
    private val retryFunction: () -> Unit,
    private val onItemClick: KFunction1<Int, Unit>,
    private val resources: Resources
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var loadingFlg = true
    private var retryFlg = false

    enum class ItemType(val value: Int) {
        UserItem(1),
        ProgressBarItem(2),
        RetryItem(3)
    }

    fun showLoading() {
        retryFlg = false
        loadingFlg = true
        notifyItemInserted(Users.size)
    }

    fun hideLoading() {
        loadingFlg = false
        retryFlg = false
        notifyItemRemoved(Users.size)
    }

    fun showRetry() {
        loadingFlg = false
        retryFlg = true
        notifyItemChanged(Users.size - 1)
    }

    fun hideRetry() {
        loadingFlg = true
        retryFlg = false
        notifyItemChanged(Users.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.ProgressBarItem.value -> ProgressBarHolder.create(parent)
            ItemType.RetryItem.value -> RetryHolder.create(parent)
            else -> UserHolder.create(parent)
        }
    }

    override fun getItemCount(): Int {
        return Users.size +
                if (loadingFlg || retryFlg)
                    1
                else
                    0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (Users.size > position)
            Users[position]?.let {
                if (holder is UserHolder) holder.bind(it, onItemClick, resources)
            }
        if (holder is RetryHolder) holder.bind(retryFunction)
    }

    override fun getItemViewType(position: Int): Int {
        if (position == Users.size) {
            if (loadingFlg)
                return ItemType.ProgressBarItem.value
            if (retryFlg)
                return ItemType.RetryItem.value
        }
        return ItemType.UserItem.value
    }
}