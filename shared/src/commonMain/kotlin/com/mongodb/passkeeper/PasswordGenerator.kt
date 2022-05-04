package com.mongodb.passkeeper

import kotlin.random.Random

fun getNewPassword(length: Int): String {
    return (1..length)
        .map { Random.nextInt(33, 126).toChar() }
        .joinToString("")
}