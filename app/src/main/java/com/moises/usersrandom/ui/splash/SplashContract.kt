package com.moises.usersrandom.ui.splash

interface SplashContract {

    interface View {

        fun startEntranceTransition()

        fun startExitTransition()
    }

    interface Presenter {

        fun addView(view: View)

        fun startDelay()

        fun doClear()
    }
}