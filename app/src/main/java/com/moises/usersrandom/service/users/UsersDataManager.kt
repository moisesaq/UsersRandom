package com.moises.usersrandom.service.users

import com.moises.usersrandom.model.User
import com.moises.usersrandom.service.APIServices
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class UsersDataManager
    @Inject
    constructor(private val api: APIServices): UsersDataContract{

    override fun getUsers(): Observable<List<User>> {
        return api.users().flatMapObservable { Observable.just(it.results) }
                .flatMapIterable { it }
                .map { it.getItem() }
                .toList()
                .toObservable()
        }

    override fun findUserBy(id: String): Observable<User> {
        return api.userBy(id).flatMapObservable { Observable.just(it.results) }
                .map { it[0].getItem() }
    }
}
