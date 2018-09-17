package com.moises.usersrandom.injection

import com.moises.usersrandom.injection.scopes.ScopeActivity
import com.moises.usersrandom.injection.users.UsersFragmentModule
import com.moises.usersrandom.ui.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScopeActivity
@Subcomponent(modules = arrayOf(MainActivityModule::class, UsersFragmentModule::class))
interface MainActivityComponent: AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<MainActivity>()
}