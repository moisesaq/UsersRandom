package com.moises.usersrandom.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.moises.usersrandom.R
import com.moises.usersrandom.ui.users.UsersFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector,
        UsersFragment.OnUsersFragmentListener {

    @Inject lateinit var injector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var usersFragment: UsersFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        setContentView(R.layout.activity_main)
        replaceFragment(usersFragment, false)
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

    override fun onUserClicked(userId: String) {
    }
}
