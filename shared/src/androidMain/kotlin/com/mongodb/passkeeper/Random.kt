package com.mongodb.passkeeper

import java.util.*

actual class RandomUUID {
    actual val randomId: String
        get() = UUID.randomUUID().toString()
}