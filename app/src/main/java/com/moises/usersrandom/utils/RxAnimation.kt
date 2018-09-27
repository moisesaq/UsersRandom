package com.moises.usersrandom.utils

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.support.v4.view.ViewCompat
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
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
    return translate("x", duration, from, to)
}

fun View.translateY(duration: Long = 500, from: Float, to: Float): Completable {
    return translate("y", duration, from, to)
}

private fun View.translate(direction: String, duration: Long = 500, from: Float, to: Float): Completable {
    val animationSubject = CompletableSubject.create()
    val objectAnimator = ObjectAnimator.ofFloat(this, direction, from, to)
    objectAnimator.run {
        this.duration = duration
        interpolator = AccelerateDecelerateInterpolator()
        addListener(object: AnimationListener(){
            override fun onAnimationEnd(p0: Animator?) {
                animationSubject.onComplete()
            }
        })
        start()
    }
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
    val animatorX = ObjectAnimator.ofFloat(this, "scaleX", 0f, 1.0f)
    val animatorY = ObjectAnimator.ofFloat(this, "scaleY", 0f, 1.0f)

    val animatorSet = AnimatorSet()
    animatorSet.apply {
        playTogether(animatorY, animatorX)
        this.duration = duration
        interpolator = AccelerateDecelerateInterpolator()
        addListener(object : AnimationListener() {
            override fun onAnimationEnd(p0: Animator?) {
                animationSubject.onComplete()
            }
        })
        start()
    }
    return animationSubject
}

fun View.scaleFromTop(duration: Long = 200): Completable {
    val animationSubject = CompletableSubject.create()
    val animation = ScaleAnimation(
            1f, 1f, // Start and end values for the X axis scaling
            0f, 1f, // Start and end values for the Y axis scaling
            Animation.RELATIVE_TO_SELF, 0f, // Pivot point of X scaling
            Animation.RELATIVE_TO_SELF, 0f) // Pivot point of Y scaling

    animation.run {
        fillAfter = true
        this.duration = duration
        interpolator = AccelerateDecelerateInterpolator()
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(p0: Animation?) {
                animationSubject.onComplete()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationStart(p0: Animation?) {
            }
        })
        startAnimation(this)
    }
    return animationSubject
}

fun View.scaleFromCenter(duration: Long = 200): Completable {
    val animationSubject = CompletableSubject.create()
    //val valueX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1F, 1F)
    val valueY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0F, 1F)

    val animatorY = ObjectAnimator.ofPropertyValuesHolder(this, valueY)
    val animatorAlpha = ObjectAnimator.ofFloat(this, View.ALPHA, 0f, 1.0f)

    val animatorSet = AnimatorSet()
    animatorSet.apply {
        playTogether(animatorY, animatorAlpha)
        this.duration = duration
        interpolator = AccelerateDecelerateInterpolator()
        addListener(object : AnimationListener() {
            override fun onAnimationEnd(p0: Animator?) {
                animationSubject.onComplete()
            }
        })
        start()
    }
    return animationSubject
}