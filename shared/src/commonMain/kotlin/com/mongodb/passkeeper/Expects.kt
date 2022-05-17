package com.mongodb.passkeeper

expect class BuildType() {
    val isDebug: Boolean
}

expect class RandomUUID(){
    val randomId : String
}

