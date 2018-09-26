package com.moises.usersrandom.ui.users

import com.moises.usersrandom.model.User

interface UsersContract {

    //TODO Create a base view
    interface View {

        fun showLoading()

        fun showUsers(users: List<User>)

        fun hideLoading()

        fun showError(error: String)
    }

    //TODO Create a base presenter
    interface Presenter {

        fun addView(view: View)

        fun loadUsers()

        fun doDispose()
    }
}