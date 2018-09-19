package com.moises.usersrandom.injection.splash

import com.moises.usersrandom.injection.scopes.ScopeActivity
import com.moises.usersrandom.ui.splash.SplashActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScopeActivity
@Subcomponent(modules = [SplashActivityModule::class])
interface SplashActivityComponent: AndroidInjector<SplashActivity> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<SplashActivity>()
}