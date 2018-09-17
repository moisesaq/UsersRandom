package com.moises.usersrandom.ui.userInfo

import com.moises.usersrandom.model.User

interface UserInfoContract {

    interface View {

        fun showLoading()

        fun showUserInfo(user: User)

        fun hideLoading()

        fun showError(error: String)
    }

    interface Presenter {

        fun addView(view: View)

        fun findUser(id: String)

        fun doClear()
    }
}