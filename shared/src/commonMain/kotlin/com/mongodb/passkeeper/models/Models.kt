package com.mongodb.passkeeper.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey


class PasswordInfo :  RealmObject {

    @PrimaryKey
    var _id: String = ""

    var url: String = ""

    var password: String = ""

    var name: String = ""

    var userId: String = ""

}