package com.moises.usersrandom.ui.splash

import android.animation.Animator
import android.os.Build
import android.os.Bundle
import android.support.transition.*
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View.*
import android.view.ViewAnimationUtils
import android.view.WindowManager
import com.moises.usersrandom.R
import com.moises.usersrandom.ui.MainActivity
import com.moises.usersrandom.ui.base.BaseActivity
import com.moises.usersrandom.utils.*
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashContract.View {

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
            //goToMainActivity()
            presenter.startDelay()
        }
        btnEndAnimate.setOnClickListener {
            startAnimationCircularReveal()
        }
        presenter.startDelay()
    }

    override fun startEntranceTransition() {
        beginTransition()
    }

    private fun beginTransition() {
        activityView.slideTopTransition()
                .subscribe {
                    animateLogo()
                }
        imageView.visibility = VISIBLE
    }

    private fun animateLogo() {
        imageView.scaleIn()
                .doAfterTerminate {
                    imageView.rotationYBy(y = 360f)
                }.doAfterTerminate {
                    imageView.scaleOut()
                }.doAfterTerminate {
                    progressBar.visibility = VISIBLE
                    progressBar.translateX(from =  -activityView.width.toFloat(), to = 0f)
                }
                .subscribe()
    }

    override fun startExitTransition() {
        imageView.scale(500, -1f)
                .doAfterTerminate {
                    progressBar.translateX(from = activityView.x, to = activityView.width.toFloat())
                }.andThen(progressBar.rotationXBy(x = 360F))
                .subscribe {
                    startAnimationCircularReveal()
                }
    }

    private fun goToMainActivity() {
        MainActivity.start(this)
        this.finish()
    }

    private fun startAnimationCircularReveal() {
        val cx = width / 2
        val cy = heght / 2
        val white = ContextCompat.getColor(this, android.R.color.white)
        val startRadius = Math.hypot(width.toDouble(), heght.toDouble()).toFloat()
        val finalRadius = 0F
        activityView.circleReveal(1000, width, 0, startRadius, finalRadius)
                .subscribe {
                    activityView.setBackgroundColor(white)
                    goToMainActivity()
                }
    }
}