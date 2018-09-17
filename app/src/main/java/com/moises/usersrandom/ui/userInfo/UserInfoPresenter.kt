package com.moises.usersrandom.ui.userInfo

import com.moises.usersrandom.service.users.UsersDataContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserInfoPresenter
    @Inject
    constructor(private val manager: UsersDataContract): UserInfoContract.Presenter {

    companion object {
        const val TAG = "UsersPresenter"
    }
    private lateinit var view: UserInfoContract.View
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun addView(view: UserInfoContract.View) {
        this.view = view
    }

    override fun findUser(id: String) {
        view.showLoading()
        manager.findUserBy(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { view.showUserInfo(it) },
                        onComplete = { view.hideLoading() },
                        onError = { error(it)}
                ).addTo(compositeDisposable)
    }

    override fun doClear() {

    }
}