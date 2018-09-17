package com.moises.usersrandom.service.users

import com.moises.usersrandom.model.User
import io.reactivex.Observable

interface UsersDataContract {

    fun getUsers(): Observable<List<User>>
}