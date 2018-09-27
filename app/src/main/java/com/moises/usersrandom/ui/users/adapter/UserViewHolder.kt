package com.moises.usersrandom.ui.users.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.moises.usersrandom.model.User
import com.moises.usersrandom.utils.appear
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.user_item.view.*
import java.util.concurrent.TimeUnit

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val ivPhoto: ImageView = view.iv_user_photo
    private val tvName: TextView = view.tv_user_name

    fun bind(user: User, delay: Long, clickListener: (User, View) -> Unit) {
        tvName.text = user.name
        itemView.setOnClickListener {
            clickListener(user, itemView)
        }
        loadPhoto(user.photo)
        animateItemView(delay)
    }

    private fun loadPhoto(url: String) {
        Glide.with(itemView.context)
                .load(url)
                .into(ivPhoto)
    }

    private fun animateItemView(delay: Long) {
        Observable.timer(delay, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate { animateView() }
                .subscribe() //TODO: Here, For this create a doDispose
    }

    private fun animateView() {
        itemView.run {
            visibility = View.VISIBLE
            appear()
        }
    }


}