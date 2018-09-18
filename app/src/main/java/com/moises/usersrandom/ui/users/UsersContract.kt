package com.moises.usersrandom.ui.users

import com.moises.usersrandom.model.User

interface UsersContract {

    interface View {

        fun showLoading()

        fun showUsers(users: List<User>)

        fun hideLoading()

        fun showError(error: String)
    }

    interface Presenter {

        fun addView(view: View)

        fun loadUsers()

        fun doClear()
    }
}