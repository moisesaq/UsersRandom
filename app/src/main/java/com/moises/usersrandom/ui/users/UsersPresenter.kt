package com.moises.usersrandom.ui.users

import android.util.Log
import com.moises.usersrandom.repository.service.users.UsersDataContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@Deprecated("Replace with UsersViewModel")
class UsersPresenter
@Inject
constructor(private val manager: UsersDataContract) : UsersContract.Presenter {

    companion object {
        const val TAG = "UsersPresenter"
    }

    private lateinit var view: UsersContract.View

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun addView(view: UsersContract.View) {
        this.view = view
    }

    override fun loadUsers() {
        view.showLoading()
        manager.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { view.showUsers(it) },
                        onComplete = { view.hideLoading() },
                        onError = { error(it) }
                ).addTo(compositeDisposable)
    }

    private fun error(throwable: Throwable) {
        Log.e(TAG, "Error: " + throwable.toString())
        view.showError(throwable.localizedMessage)
    }

    override fun composite(): CompositeDisposable = compositeDisposable

    override fun doDispose() {
        compositeDisposable.clear()
    }
}