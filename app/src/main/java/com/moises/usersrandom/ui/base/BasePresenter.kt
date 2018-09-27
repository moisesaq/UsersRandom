package com.moises.usersrandom.ui.base

import io.reactivex.disposables.CompositeDisposable

interface BasePresenter<T> {

    fun addView(view: T)

    fun composite(): CompositeDisposable

    fun doDispose()
}