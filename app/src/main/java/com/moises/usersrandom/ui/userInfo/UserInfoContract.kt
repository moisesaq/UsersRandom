package com.moises.usersrandom.ui.userInfo

import com.moises.usersrandom.model.User
import io.reactivex.disposables.CompositeDisposable

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

        fun composite(): CompositeDisposable

        fun doDispose()
    }
}