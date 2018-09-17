package com.moises.usersrandom.ui.users

import com.moises.usersrandom.model.User


interface UsersContract {

    interface View {

        fun showUsers(users: List<User>)

        fun showError(error: String)
    }

    interface Presenter {

        fun addView(view: View)

        fun loadUsers()

        fun doClear()
    }
}