package com.moises.usersrandom.service

import com.moises.usersrandom.service.users.UsersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface APIServices {

    @GET("/api/?results=15")
    fun users(): Single<UsersResponse>

    @GET("/api/?id={id}")
    fun userBy(@Path("id") id: String): Single<UsersResponse>
}