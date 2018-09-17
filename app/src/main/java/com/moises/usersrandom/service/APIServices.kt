package com.moises.usersrandom.service

import com.moises.usersrandom.service.users.UsersResponse
import io.reactivex.Single
import retrofit2.http.GET

interface APIServices {

    @GET("/api/?results=5")
    fun users(): Single<UsersResponse>
}