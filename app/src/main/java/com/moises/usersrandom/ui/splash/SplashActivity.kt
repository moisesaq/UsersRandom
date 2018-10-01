package com.moises.usersrandom.ui.splash

import android.os.Bundle
import android.transition.Explode
import android.transition.Slide
import android.view.Gravity
import android.view.View.*
import android.view.Window
import android.view.WindowManager
import com.moises.usersrandom.R
import com.moises.usersrandom.ui.MainActivity
import com.moises.usersrandom.ui.base.BaseActivity
import com.moises.usersrandom.utils.*
import dagger.android.AndroidInjection
import io.reactivex.Completable
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setUpTransition()
        setContentView(R.layout.activity_splash)
        presenter.addView(this)
        setUp()
    }

    private fun setUpTransition() {
        window.run {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            enterTransition = Slide(Gravity.END)
            exitTransition = Slide(Gravity.START)
        }
    }

    private fun setUp() {
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        presenter.startDelay()
    }

    override fun startEntranceTransition() {
        splashView.slideTopTransition()
                .subscribe { animateImageView() }
        imageView.visibility = VISIBLE
    }

    private fun animateImageView() {
        imageView.run {
            scaleIn().doAfterTerminate { rotationYBy(y = 360f) }
                    .doAfterTerminate { scaleOut() }
                    .doAfterTerminate { animateProgressBarToEnter() }
                    .subscribe()
        }
    }

    private fun animateProgressBarToEnter(): Completable {
        return progressBar.run {
            visibility = VISIBLE
            translateX(from =  -splashView.width.toFloat(), to = 0f)
        }
    }

    override fun startExitTransition() {
        imageView.scale(500, -1f)
                .doAfterTerminate { animateProgressBarToExit() }
                .subscribe {
                    startAnimationCircularReveal()
                }
    }

    private fun animateProgressBarToExit(): Completable {
        return progressBar.run {
            translateX(from = splashView.x, to = splashView.width.toFloat()).andThen(rotationXBy(x = 360F))
        }
    }

    private fun startAnimationCircularReveal() {
        val startRadius = Math.hypot(width.toDouble(), heght.toDouble()).toFloat()
        splashView.run {
            circleReveal(600, width, 0, startRadius, 0F)
                    .subscribe {
                        setBackgroundColor(colorWhite)
                        goToMainActivity()
                    }
        }
    }

    private fun goToMainActivity() {
        MainActivity.start(this)
        this.finish()
    }
}