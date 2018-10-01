package com.moises.usersrandom.injection.app

import com.moises.usersrandom.injection.MainActivityModule
import com.moises.usersrandom.UsersApp
import com.moises.usersrandom.injection.splash.SplashActivityModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class,
    SplashActivityModule::class, MainActivityModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(usersApp: UsersApp): Builder

        fun build(): AppComponent
    }

    fun inject(usersApp: UsersApp)
}