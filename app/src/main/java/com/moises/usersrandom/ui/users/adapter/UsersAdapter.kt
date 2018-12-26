package com.moises.usersrandom.ui.users.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moises.usersrandom.R
import com.moises.usersrandom.model.User
import javax.inject.Inject

class UsersAdapter
@Inject
constructor() : RecyclerView.Adapter<UserViewHolder>() {

    private var items = listOf<User>()
    private lateinit var clickListener: (User, View) -> Unit
    private var delayTime: Long = 0

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(items[position], calculateDelayTime(), clickListener)
    }

    fun addItems(items: List<User>) {
        delayTime = 0
        this.items = items
        notifyDataSetChanged()
    }

    fun addClickListener(listener: (User, View) -> Unit) {
        clickListener = listener
    }

    private fun calculateDelayTime(): Long {
        delayTime += 200
        return delayTime

    }
}

