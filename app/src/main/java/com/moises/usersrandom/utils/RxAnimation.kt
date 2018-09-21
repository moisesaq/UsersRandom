package com.moises.usersrandom.utils

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.support.v4.view.ViewCompat
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import io.reactivex.Completable
import io.reactivex.subjects.CompletableSubject

fun View.rotationYBy(duration: Long = 500, y: Float): Completable {
    val animationSubject = CompletableSubject.create()
    ViewCompat.animate(this)
            .setDuration(duration)
            .rotationYBy(y)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .withEndAction {
                animationSubject.onComplete()
            }
    return animationSubject
}

fun View.rotationXBy(duration: Long = 500, x: Float): Completable {
    val animationSubject = CompletableSubject.create()
    ViewCompat.animate(this)
            .setDuration(duration)
            .rotationXBy(x)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .withEndAction {
                animationSubject.onComplete()
            }
    return animationSubject
}

fun View.translateX(duration: Long = 500, from: Float, to: Float): Completable {
    val animationSubject = CompletableSubject.create()
    val objectAnimator = ObjectAnimator.ofFloat(this, "x", from, to)
    objectAnimator.duration = duration
    objectAnimator.interpolator = AccelerateDecelerateInterpolator()
    objectAnimator.addListener(object: AnimationListener(){
        override fun onAnimationEnd(p0: Animator?) {
            animationSubject.onComplete()
        }
    })
    objectAnimator.start()
    return animationSubject
}

fun View.scaleIn(xy: Float = 1.5f): Completable {
    return scale(500, xy)
}

fun View.scaleOut(xy: Float = -1.5f): Completable {
    return scale(500, xy)
}

fun View.scale(duration: Long = 500, xy: Float): Completable {
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

fun View.appear(duration: Long = 200): Completable {
    val animationSubject = CompletableSubject.create()
    val anim1 = ObjectAnimator.ofFloat(this, "scaleX", 0f, 1.0f)
    val anim2 = ObjectAnimator.ofFloat(this, "scaleY", 0f, 1.0f)

    val animatorSet = AnimatorSet()
    animatorSet.playTogether(anim2, anim1)
    animatorSet.duration = duration
    animatorSet.interpolator = AccelerateDecelerateInterpolator()
    animatorSet.addListener(object : AnimationListener() {
        override fun onAnimationEnd(p0: Animator?) {
            animationSubject.onComplete()
        }
    })
    animatorSet.start()
    return animationSubject
}