package com.mongodb.passkeeper

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}