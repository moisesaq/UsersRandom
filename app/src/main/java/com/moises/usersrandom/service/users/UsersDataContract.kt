package com.moises.usersrandom.service.users

import com.moises.usersrandom.model.User
import io.reactivex.Observable
import io.reactivex.Single

interface UsersDataContract {

    fun getUsers(): Observable<List<User>>

    fun findUserBy(id: String): Observable<User>
}