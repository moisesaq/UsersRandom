package com.moises.usersrandom.ui.splash

import android.animation.ValueAnimator
import android.os.Bundle
import android.support.transition.Slide
import android.support.transition.TransitionListenerAdapter
import android.support.transition.TransitionManager
import android.support.transition.TransitionSet
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.widget.Toast
import com.moises.usersrandom.R
import com.moises.usersrandom.ui.MainActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter.addView(this)
        setUp()
    }

    private fun setUp() {

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        btnAnimate.setOnClickListener {
            startAnimation()
        }
        imageView.setOnClickListener {
            animation3()
        }
    }

    private fun testTransition() {
        val set = TransitionSet()
                .addTransition(Slide(Gravity.BOTTOM))
        TransitionManager.beginDelayedTransition(activityView, set)
        if (imageView.visibility == View.VISIBLE) {
            imageView.visibility = View.GONE
        } else {
            imageView.visibility = View.VISIBLE
        }

    }

    override fun onResume() {
        super.onResume()
        //presenter.startDelay()
    }

    override fun startAnimation() {
        animation1()
    }

    private fun animation1() {
        imageView.animate()
                .setDuration(500)
                .translationYBy(500f)
                .scaleXBy(0.5f)
                .scaleYBy(0.5f)
                .setInterpolator(AccelerateInterpolator(1.5f))
                .withEndAction {
                    showMessage("Finish animation")
                    Runnable {
                        animation3()
                    }.run()
                }
                .start()
    }

    private fun animation2() {
        val valueAnimator = ValueAnimator.ofFloat(imageView.pivotY, activityView.height.toFloat() / 2)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            imageView.translationY = value
        }
        valueAnimator.interpolator = AccelerateInterpolator(1.5f)
        valueAnimator.duration = 1500
        valueAnimator.addUpdateListener {
            animation3()
        }
        valueAnimator.start()
    }

    private fun animation3() {
        imageView.animate()
                .setDuration(500)
                .rotationXBy(180f)
                .rotationYBy(180f)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .withEndAction {
                    showMessage("Finish animation")
                }
                .start()
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun stopAnimation() {
        MainActivity.start(this)
        this.finish()
    }
}