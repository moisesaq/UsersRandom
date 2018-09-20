package com.moises.usersrandom.ui.splash

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashPresenter @Inject constructor(): SplashContract.Presenter {

    lateinit var view: SplashContract.View
    private val compositeDisposable = CompositeDisposable()

    override fun addView(view: SplashContract.View) {
        this.view = view
    }

    override fun startDelay() {
        view.startEntranceTransition()
        Observable.timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onComplete = { view.startExitTransition() },
                    onError = { Log.e("SplashPresenter", it.message) }
                ).addTo(compositeDisposable)
    }

    override fun doClear() {
        compositeDisposable.clear()
    }
}