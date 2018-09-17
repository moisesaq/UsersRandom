package com.moises.usersrandom.injection

import android.app.Activity
import com.moises.usersrandom.injection.scopes.ScopeActivity
import com.moises.usersrandom.ui.MainActivity
import com.moises.usersrandom.ui.users.UsersFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(UsersFragmentComponent::class))
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindMainActivityInjectorFactory(builder: MainActivityComponent.Builder):
            AndroidInjector.Factory<out Activity>

    @Module
    object MainActivityModule {
        @Provides
        @ScopeActivity
        fun provideUsersFragment(): UsersFragment {
            return UsersFragment()
        }
    }
}