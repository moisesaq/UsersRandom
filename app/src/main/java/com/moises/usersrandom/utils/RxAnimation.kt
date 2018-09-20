package com.moises.usersrandom.utils

import android.animation.Animator
import android.animation.ObjectAnimator
import android.support.v4.view.ViewCompat
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import io.reactivex.Completable
import io.reactivex.subjects.CompletableSubject

fun View.rotationYBy(y: Float): Completable {
    val animationSubject = CompletableSubject.create()
    ViewCompat.animate(this)
            .setDuration(500)
            .rotationYBy(y)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .withEndAction {
                animationSubject.onComplete()
            }
    return animationSubject
}

fun View.translateYBy(y: Float): Completable {
    val animationSubject = CompletableSubject.create()
    ViewCompat.animate(this)
            .setDuration(500)
            .translationYBy(y)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .withEndAction {
                animationSubject.onComplete()
            }
    return animationSubject
}

fun View.translateXBy(x: Float): Completable {
    val animationSubject = CompletableSubject.create()
    ViewCompat.animate(this)
            .setDuration(500)
            .translationXBy(x)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .withEndAction {
                animationSubject.onComplete()
            }
    return animationSubject
}

fun View.translateX(from: Float, to: Float): Completable {
    val animationSubject = CompletableSubject.create()
    val objectAnimator = ObjectAnimator.ofFloat(this, "x", from, to)
    objectAnimator.duration = 500
    objectAnimator.interpolator = AccelerateDecelerateInterpolator()
    objectAnimator.addListener(object: AnimationListener(){
        override fun onAnimationEnd(p0: Animator?) {
            animationSubject.onComplete()
        }
    })
    objectAnimator.start()
    return animationSubject
}

fun View.scaleIn(): Completable {
    return scale(500, 1.5f)
}

fun View.scaleOut(): Completable {
    return scale(500, -1.5f)
}

fun View.scale(duration: Long, xy: Float): Completable {
    val animationSubject = CompletableSubject.create()
    ViewCompat.animate(this)
            .setDuration(duration)
            .scaleXBy(xy)
            .scaleYBy(xy)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .withEndAction {
                animationSubject.onComplete()
            }
    return animationSubject
}