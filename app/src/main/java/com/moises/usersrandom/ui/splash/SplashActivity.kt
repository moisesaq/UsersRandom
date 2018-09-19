package com.moises.usersrandom.ui.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
    }

    override fun onResume() {
        super.onResume()
        presenter.startDelay()
    }

    override fun startAnimation() {
        imageView.animate()
                .setDuration(3000)
                .translationY(500f).start()
    }

    override fun stopAnimation() {
        MainActivity.start(this)
        this.finish()
    }
}