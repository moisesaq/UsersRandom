package com.moises.usersrandom.injection.splash

import android.app.Activity
import com.moises.usersrandom.injection.scopes.ScopeActivity
import com.moises.usersrandom.ui.splash.SplashActivity
import com.moises.usersrandom.ui.splash.SplashPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class SplashActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(SplashActivity::class)
    abstract fun bindSplashActivityInjectorFactory(builder: SplashActivityComponent.Builder):
            AndroidInjector.Factory<out Activity>

    @Module
    object SplashActivityModule {
        @Provides
        @ScopeActivity
        fun provideSplashPresenter(): SplashPresenter {
            return SplashPresenter()
        }
    }
}