package com.moises.usersrandom.utils

import android.animation.Animator
import android.graphics.Rect
import android.support.transition.*
import android.view.Gravity
import android.view.View
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

fun ViewGroup.explodeAndEpicenter(clickedView: View): Completable {
    val animationSubject = CompletableSubject.create()
    val viewRec = Rect()
    clickedView.getGlobalVisibleRect(viewRec)
    val explode = Explode().excludeChildren(clickedView, true)
    explode.epicenterCallback = object : Transition.EpicenterCallback() {
        override fun onGetEpicenter(p0: Transition): Rect {
            return viewRec
        }
    }

    val fade = Fade().addTarget(clickedView)
    val set = TransitionSet()
            .addTransition(explode)
            .addTransition(fade)
            .addListener(object : TransitionListener() {
                override fun onTransitionEnd(p0: Transition) {
                    animationSubject.onComplete()
                }
            })

    set.excludeChildren(clickedView, true)

    TransitionManager.beginDelayedTransition(this, set)
    return animationSubject
}