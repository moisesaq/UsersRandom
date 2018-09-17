package com.moises.usersrandom.injection.app

import android.content.Context
import com.moises.usersrandom.injection.MainActivityComponent
import com.moises.usersrandom.service.APIServices
import com.moises.usersrandom.service.users.UsersDataContract
import com.moises.usersrandom.service.users.UsersDataManager
import com.moises.usersrandom.ui.UsersApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = arrayOf(MainActivityComponent::class))
class AppModule {

    @Singleton
    @Provides
    fun provideContext(usersApp: UsersApp): Context {
        return usersApp.applicationContext
    }

    @Singleton
    @Provides
    fun provideUsersDataManager(api: APIServices): UsersDataContract {
        return UsersDataManager(api)
    }
}