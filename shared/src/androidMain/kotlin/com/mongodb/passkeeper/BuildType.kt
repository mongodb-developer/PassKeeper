package com.mongodb.passkeeper

actual class BuildType {
    actual val isDebug: Boolean
        get() = BuildConfig.DEBUG
}