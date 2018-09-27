package com.moises.usersrandom.ui

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.transition.Explode
import android.transition.Slide
import android.view.Gravity
import android.view.View.VISIBLE
import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import com.moises.usersrandom.R
import com.moises.usersrandom.model.User
import com.moises.usersrandom.ui.base.BaseActivity
import com.moises.usersrandom.ui.userInfo.UserInfoFragment
import com.moises.usersrandom.ui.users.OnUsersFragmentListener
import com.moises.usersrandom.ui.users.UsersFragment
import com.moises.usersrandom.utils.appear
import com.moises.usersrandom.utils.circleReveal
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector,
        OnUsersFragmentListener {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var usersFragment: UsersFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setUpTransition()
        setContentView(R.layout.activity_main)
        setUp()
    }

    private fun setUpTransition() {
        window.run {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            enterTransition = Slide(Gravity.START)
            exitTransition = Slide(Gravity.END)
        }
    }

    private fun setUp() {
        showOrHideStatusBar(false)
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setHomeButtonEnabled(true)
        }
        startAnimationCircularReveal()
    }

    private fun replaceFragment(fragment: Fragment, addToStack: Boolean = true) {
        supportFragmentManager.beginTransaction().run {
            if (addToStack)
                addToBackStack(fragment::class.java.name)
            replace(R.id.mainView, fragment)
            commit()
        }
    }

    override fun onUserClicked(user: User) {
        replaceFragment(UserInfoFragment.newInstance(user))
    }

    private fun startAnimationCircularReveal() {
        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapCompletable { startCircleReveal() }
                .doAfterTerminate { animateToolbar() }
                .subscribe {
                    changeBackground(colorWhite)
                    showOrHideStatusBar(true)
                    replaceFragment(usersFragment, false)
                }
    }

    private fun startCircleReveal(): Completable {
        mainView.visibility = VISIBLE
        val finalRadius = Math.hypot(width.toDouble(), heght.toDouble()).toFloat()
        return mainView.circleReveal(600, 0, 0, 0F, finalRadius)
    }

    private fun animateToolbar(): Completable {
        return appBarLayout.run { visibility = VISIBLE; appear() }
    }

    private fun showOrHideStatusBar(visibility: Boolean) {
        if (visibility) {
            window.clearFlags(FLAG_FULLSCREEN)
        } else {
            window.addFlags(FLAG_FULLSCREEN)
        }
    }

    private fun changeBackground(color: Int) {
        mainView.setBackgroundColor(color)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return injector
    }

    companion object {
        @JvmStatic
        fun start(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        }
    }
}
