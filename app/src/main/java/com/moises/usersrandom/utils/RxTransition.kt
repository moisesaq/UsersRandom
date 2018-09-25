package com.moises.usersrandom.utils

import android.animation.Animator
import android.support.transition.Slide
import android.support.transition.Transition
import android.support.transition.TransitionManager
import android.support.transition.TransitionSet
import android.view.Gravity
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import io.reactivex.Completable
import io.reactivex.subjects.CompletableSubject

fun ViewGroup.circleReveal(duration: Long = 1000, cx: Int, cy: Int,
                           startRadius: Float, finalRadius: Float): Completable{
    val animationSubject = CompletableSubject.create()
    val animator = ViewAnimationUtils
            .createCircularReveal(this, cx, cy, startRadius, finalRadius)
    animator.duration = duration
    animator.start()
    animator.addListener(object: AnimationListener() {
        override fun onAnimationEnd(p0: Animator?) {
            animationSubject.onComplete()
        }
    })
    return animationSubject
}

fun ViewGroup.slideTopTransition(): Completable {
    val animationSubject = CompletableSubject.create()
    val set = TransitionSet()
            .addTransition(Slide(Gravity.TOP))
            .addListener(object : TransitionListener() {
                override fun onTransitionEnd(p0: Transition) {
                    animationSubject.onComplete()
                }
            })
    TransitionManager.beginDelayedTransition(this, set)
    return animationSubject
}