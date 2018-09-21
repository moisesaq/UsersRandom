package com.moises.usersrandom.ui.splash

import android.os.Bundle
import android.support.transition.*
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.WindowManager
import com.moises.usersrandom.R
import com.moises.usersrandom.ui.MainActivity
import com.moises.usersrandom.utils.*
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.concurrent.TimeUnit
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
            presenter.startDelay()
        }
        presenter.startDelay()
    }

    override fun startEntranceTransition() {
        val set = TransitionSet()
                .addTransition(Slide(Gravity.TOP))
                .addListener(object : TransitionListener() {
                    override fun onTransitionEnd(p0: Transition) {
                        animateLogo()
                    }
                })
        TransitionManager.beginDelayedTransition(activityView, set)
        imageView.visibility = if (imageView.visibility == VISIBLE) GONE else VISIBLE
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
                }
                .delay(2, TimeUnit.SECONDS)
                .subscribe {
                    goToMainActivity()
                }
    }

    private fun goToMainActivity() {
        MainActivity.start(this)
        this.finish()
    }
}