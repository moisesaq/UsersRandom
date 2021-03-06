package com.moises.usersrandom.model

class UserParser {

    lateinit var id: ID
    lateinit var name: Name
    lateinit var picture: Picture

    fun getItem(): User {
        var userId = ""
        id.value?.let {
            userId = it
        }
        return User(userId, name.first, name.last, picture.large)
    }
}

class Name {
    var first: String = ""
    var last: String = ""
}

class Picture {
    var large: String = ""
}

class ID {
    var value: String? = ""
}