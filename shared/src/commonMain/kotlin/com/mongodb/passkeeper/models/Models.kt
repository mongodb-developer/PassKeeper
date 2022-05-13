package com.mongodb.passkeeper.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


class PasswordInfo : RealmObject {

    @PrimaryKey
    var _id: String = ""

    var url: String = ""

    var password: String = ""

    var name: String = ""

    var _partitionKey: String = ""

}