package com.moises.usersrandom.service.users

import com.moises.usersrandom.model.User
import com.moises.usersrandom.service.APIServices
import io.reactivex.Observable
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
}
