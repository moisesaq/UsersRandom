package com.moises.usersrandom.injection.users

import com.moises.usersrandom.injection.scopes.ScopeFragment
import com.moises.usersrandom.ui.users.UsersFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScopeFragment
@Subcomponent(modules = [UsersFragmentModule::class])
interface UsersFragmentComponent: AndroidInjector<UsersFragment> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<UsersFragment>()
}