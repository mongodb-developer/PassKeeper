package com.mongodb.passkeeper

import platform.Foundation.NSUUID

actual class RandomUUID {
    actual val randomId: String
        get() = NSUUID.UUID().UUIDString
}