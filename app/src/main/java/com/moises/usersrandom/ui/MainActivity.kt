package com.moises.usersrandom.ui

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.transition.Slide
import android.view.Gravity
import android.view.View.VISIBLE
import android.view.Window
import android.view.WindowManager
import com.moises.usersrandom.R
import com.moises.usersrandom.model.User
import com.moises.usersrandom.ui.base.BaseActivity
import com.moises.usersrandom.ui.userInfo.UserInfoFragment
import com.moises.usersrandom.ui.users.OnUsersFragmentListener
import com.moises.usersrandom.ui.users.UsersFragment
import com.moises.usersrandom.utils.appear
import com.moises.usersrandom.utils.circleReveal
import com.moises.usersrandom.utils.scale
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash.*
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
        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            enterTransition = Slide(Gravity.END)
        }
        setContentView(R.layout.activity_main)
        setUpActionBar()
    }


    private fun setUpActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setHomeButtonEnabled(true)
        }
        startAnimationCircularReveal()
    }

    private fun replaceFragment(fragment: Fragment, addToStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        if (addToStack)
            transaction.addToBackStack(fragment::class.java.name)
        transaction.replace(R.id.content_main, fragment)
        transaction.commit()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return injector
    }

    override fun onUserClicked(user: User) {
        replaceFragment(UserInfoFragment.newInstance(user), true)
    }

    private fun startAnimationCircularReveal() {
        val finalRadius = Math.hypot(width.toDouble(), heght.toDouble()).toFloat()
        Observable.timer(1, TimeUnit.SECONDS).flatMapCompletable {
                content_main.circleReveal(700, 0, 0, 0F, finalRadius)
                }.doAfterTerminate {
                    toolbar.visibility = VISIBLE
                    toolbar.appear()
                }.subscribe {
                    replaceFragment(usersFragment, false)
                }
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}
