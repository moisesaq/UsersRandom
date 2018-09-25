package com.moises.usersrandom.ui.splash

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View.*
import android.view.WindowManager
import com.moises.usersrandom.R
import com.moises.usersrandom.ui.MainActivity
import com.moises.usersrandom.ui.base.BaseActivity
import com.moises.usersrandom.utils.*
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_splash.*
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
            presenter.startDelay()
        }
        btnEndAnimate.setOnClickListener {
            startAnimationCircularReveal()
        }
        presenter.startDelay()
    }

    override fun startEntranceTransition() {
        activityView.slideTopTransition()
                .subscribe {
                    animateLogo()
                }
        imageView.visibility = VISIBLE
    }

    private fun animateLogo() {
        imageView.scaleIn().doAfterTerminate {
                    imageView.rotationYBy(y = 360f)
                }.doAfterTerminate {
                    imageView.scaleOut()
                }.doAfterTerminate {
                    progressBar.visibility = VISIBLE
                    progressBar.translateX(from =  -activityView.width.toFloat(), to = 0f)
                }.subscribe()
    }

    override fun startExitTransition() {
        imageView.scale(500, -1f)
                .doAfterTerminate {
                    progressBar.translateX(from = activityView.x, to = activityView.width.toFloat())
                }.andThen(progressBar.rotationYBy(y = 360F))
                .subscribe {
                    startAnimationCircularReveal()
                }
    }

    private fun goToMainActivity() {
        MainActivity.start(this)
        this.finish()
    }

    private fun startAnimationCircularReveal() {
        val white = ContextCompat.getColor(this, android.R.color.white)
        val startRadius = Math.hypot(width.toDouble(), heght.toDouble()).toFloat()
        activityView.circleReveal(700, width, 0, startRadius, 0F)
                .subscribe {
                    activityView.setBackgroundColor(white)
                    goToMainActivity()
                }
    }
}