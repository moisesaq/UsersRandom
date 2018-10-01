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
        val delayTime = 5L
        Observable.interval(0, 1, TimeUnit.SECONDS, Schedulers.io())
                .take(delayTime)
                .map { delayTime - it }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { startAnimation(it) },
                        onComplete = { view.startExitTransition() },
                        onError = { Log.e("SplashPresenter", it.message) }
                ).addTo(compositeDisposable)
    }

    private fun startAnimation(counter: Long) {
        Log.d("SplashPresenter", "Timer: $counter")
        if (counter == 4L) {
            view.startEntranceTransition()
        }
    }

    override fun doClear() {
        compositeDisposable.clear()
    }
}