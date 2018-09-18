package com.moises.usersrandom.injection.userInfo

import com.moises.usersrandom.injection.scopes.ScopeFragment
import com.moises.usersrandom.ui.userInfo.UserInfoFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScopeFragment
@Subcomponent(modules = [(UserInfoFragmentModule::class)])
interface UserInfoFragmentComponent: AndroidInjector<UserInfoFragment> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<UserInfoFragment>()
}