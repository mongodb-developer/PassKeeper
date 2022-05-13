package com.mongodb.passkeeper

import kotlin.native.Platform.isDebugBinary

actual class BuildType {
    actual val isDebug: Boolean
        get() = Platform.isDebugBinary
}