package com.moises.usersrandom.repository.service.users

import com.moises.usersrandom.model.User
import io.reactivex.Observable

interface UsersDataContract {

    fun getUsers(): Observable<List<User>>

    fun findUserBy(id: String): Observable<User>
}