package com.mongodb.passkeeper

import CommonFlow
import asCommonFlow
import com.mongodb.passkeeper.models.PasswordInfo
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.flow.map

class PassKeeperRepo {

    private lateinit var realmOnDevice: Realm

    private val realmApp by lazy { App.create("passkeeper-realm-hlllm") }

    private val realm by lazy {
        val user = realmApp.currentUser!!
        val config = SyncConfiguration.Builder(user, user.identity, setOf(PasswordInfo::class))
            .build()
        Realm.open(config)
    }

    init {
        realmOnDeviceSetup()
    }

    private fun realmOnDeviceSetup() {
        val schemaClass = setOf(PasswordInfo::class)

        val logLevel = if (BuildType().isDebug) {
            LogLevel.ALL
        } else {
            LogLevel.NONE
        }

        val config = RealmConfiguration
            .Builder(schema = schemaClass)
            .name("passKeeper")
            .schemaVersion(1)
            .log(logLevel)
            .build()

        realmOnDevice = Realm.open(config)
    }

    suspend fun login(userName: String, password: String): User {
        return realmApp.login(Credentials.emailPassword(userName, password))
    }

    suspend fun saveInfo(name: String, url: String, password: String) {
        val user = realmApp.currentUser ?: return

        val info = PasswordInfo().apply {
            _id = RandomUUID().randomId
            this.url = url
            this.password = password
            this.name = name
            this.userId = user.identity
        }

        realm.write {
            copyToRealm(info)
        }
    }

    fun getAllPassword(): CommonFlow<List<PasswordInfo>> {
        return realm.query(PasswordInfo::class)
            .asFlow().map {
                it.list.reversed()
            }.asCommonFlow()
    }

}