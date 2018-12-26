package com.moises.usersrandom.ui.users

import com.moises.usersrandom.model.User
import com.moises.usersrandom.ui.base.BasePresenter
import com.moises.usersrandom.ui.base.BaseView

@Deprecated("Replace with UsersViewModel")
interface UsersContract {

    interface View: BaseView {

        fun showUsers(users: List<User>)
    }

    interface Presenter: BasePresenter<View> {

        fun loadUsers()
    }
}