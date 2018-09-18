package com.moises.usersrandom.service

import com.moises.usersrandom.service.users.UsersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {

    @GET("/api/?results=15")
    fun users(): Single<UsersResponse>

    @GET("/api/")
    fun userBy(@Query("id") id: String): Single<UsersResponse>
}