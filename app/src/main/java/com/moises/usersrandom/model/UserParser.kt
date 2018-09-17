package com.moises.usersrandom.model

class UserParser {

    lateinit var name: Name
    lateinit var picture: Picture

    fun getItem(): User {
        return User(name.first, name.last, picture.medium)
    }
}

class Name {
    var first: String = ""
    var last: String = ""
}

class Picture {

    var medium: String = ""
}