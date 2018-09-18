package com.moises.usersrandom.ui.users

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.moises.usersrandom.R
import com.moises.usersrandom.model.User
import kotlinx.android.synthetic.main.user_item.view.*
import javax.inject.Inject

class UsersAdapter
@Inject
constructor() : RecyclerView.Adapter<UserViewHolder>() {

    private lateinit var items: List<User>
    private lateinit var clickListener: (User) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }

    fun addItems(items: List<User>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun addClickListener(listener: (User) -> Unit) {
        this.clickListener = listener
    }
}

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val ivPhoto: ImageView = view.iv_user_photo
    private val tvName: TextView = view.tv_user_name

    fun bind(user: User, clickListener: (User) -> Unit) {
        tvName.text = user.name
        loadPhoto(user.photo)
        itemView.setOnClickListener {
            clickListener(user)
        }
    }

    private fun loadPhoto(url: String) {
        Glide.with(itemView.context)
                .load(url)
                .into(ivPhoto)
    }
}