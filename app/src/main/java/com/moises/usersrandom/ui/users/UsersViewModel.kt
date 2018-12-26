package com.moises.usersrandom.ui.users

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.moises.usersrandom.model.User
import com.moises.usersrandom.repository.service.users.UsersDataContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UsersViewModel @Inject constructor(private val manager: UsersDataContract): ViewModel() {

    val loading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val users: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>()
    }

    val error: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun loadUsers() {
        loading.value = true
        manager.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { success(it) },
                        onComplete = { complete() },
                        onError = { error(it) }
                ).addTo(compositeDisposable)
    }

    private fun success(users: List<User>) {
        this.users.value = users
    }

    private fun complete() {
        loading.value = false
    }

    private fun error(throwable: Throwable) {
        complete()
        error.value = throwable.localizedMessage
    }

    fun doDispose() {
        compositeDisposable.clear()
    }
}