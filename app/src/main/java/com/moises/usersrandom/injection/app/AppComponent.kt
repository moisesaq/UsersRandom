package com.moises.usersrandom.injection.app

import com.moises.usersrandom.injection.MainActivityModule
import com.moises.usersrandom.ui.UsersApp
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class, MainActivityModule::class))
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(usersApp: UsersApp): Builder

        fun build(): AppComponent
    }

    fun inject(usersApp: UsersApp)
}