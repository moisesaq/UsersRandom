package com.moises.usersrandom.injection.users

import android.support.v4.app.Fragment
import com.moises.usersrandom.injection.scopes.ScopeFragment
import com.moises.usersrandom.service.users.UsersDataContract
import com.moises.usersrandom.ui.users.UsersContract
import com.moises.usersrandom.ui.users.adapter.UsersAdapter
import com.moises.usersrandom.ui.users.UsersFragment
import com.moises.usersrandom.ui.users.UsersPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module
abstract class UsersFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(UsersFragment::class)
    abstract fun bindUsersFragmentInjectorFactory(builder: UsersFragmentComponent.Builder):
            AndroidInjector.Factory<out Fragment>

    @Binds
    abstract fun bindUsersPresenter(presenter: UsersPresenter): UsersContract.Presenter

    @Module
    object UsersFragmentModule {
        @Provides
        @ScopeFragment
        fun provideUsersPresenter(manager: UsersDataContract): UsersPresenter {
            return UsersPresenter(manager)
        }

        @Provides
        @ScopeFragment
        fun provideUsersAdapter(): UsersAdapter {
            return UsersAdapter()
        }
    }
}