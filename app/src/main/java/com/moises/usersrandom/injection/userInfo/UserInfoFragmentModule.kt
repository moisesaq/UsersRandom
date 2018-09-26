package com.moises.usersrandom.injection.userInfo

import android.support.v4.app.Fragment
import com.moises.usersrandom.injection.scopes.ScopeFragment
import com.moises.usersrandom.service.users.UsersDataContract
import com.moises.usersrandom.ui.userInfo.UserInfoContract
import com.moises.usersrandom.ui.userInfo.UserInfoFragment
import com.moises.usersrandom.ui.userInfo.UserInfoPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module
abstract class UserInfoFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(UserInfoFragment::class)
    abstract fun bindUserInfoFragmentInjectorFactory(builder: UserInfoFragmentComponent.Builder):
            AndroidInjector.Factory<out Fragment>

    @Binds
    abstract fun bindUserInfoPresenter(presenter: UserInfoPresenter): UserInfoContract.Presenter
    @Module
    object UserInfoFragmentModule {
        @Provides
        @ScopeFragment
        fun provideUserInfoPresenter(manager: UsersDataContract): UserInfoPresenter {
            return UserInfoPresenter(manager)
        }
    }
}