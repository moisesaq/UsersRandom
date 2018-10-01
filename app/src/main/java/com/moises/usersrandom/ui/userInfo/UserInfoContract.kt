package com.moises.usersrandom.ui.userInfo

import com.moises.usersrandom.model.User
import com.moises.usersrandom.ui.base.BasePresenter
import com.moises.usersrandom.ui.base.BaseView
import io.reactivex.disposables.CompositeDisposable

interface UserInfoContract {

    interface View: BaseView {

        fun showUserInfo(user: User)
    }

    interface Presenter: BasePresenter<View> {

        fun findUser(id: String)
    }
}