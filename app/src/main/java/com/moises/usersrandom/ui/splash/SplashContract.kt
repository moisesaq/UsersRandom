package com.moises.usersrandom.ui.splash

interface SplashContract {

    interface View {

        fun startAnimation()

        fun stopAnimation()
    }

    interface Presenter {

        fun addView(view: View)

        fun startDelay()

        fun doClear()
    }
}