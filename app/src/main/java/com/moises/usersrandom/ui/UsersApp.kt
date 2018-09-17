package com.moises.usersrandom.ui

import android.app.Activity
import android.app.Application
import com.moises.usersrandom.injection.app.AppComponent
import com.moises.usersrandom.injection.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class UsersApp: Application(), HasActivityInjector {

    @Inject lateinit var injector: DispatchingAndroidInjector<Activity>

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        setUpDagger()
    }

    private fun setUpDagger() {
        appComponent = DaggerAppComponent
                        .builder()
                        .application(this)
                        .build()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }
}