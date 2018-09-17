package com.moises.usersrandom.model

class UserParser {

    lateinit var name: Name

    fun getItem(): User {
        return User(name.first)
    }
}

class Name {
    var first: String = ""
    var last: String = ""
}