package com.mongodb.passkeeper

import CommonFlow
import asCommonFlow
import com.mongodb.passkeeper.models.PasswordInfo
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.log.LogLevel
import io.realm.mongodb.App
import io.realm.mongodb.Credentials
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.flow.map

class PassKeeperRepo {

    private lateinit var realmOnDevice: Realm
    private val realmOnCloud by lazy { App.create("passkeeper-realm-hlllm") }


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
        return realmOnCloud.login(Credentials.emailPassword(userName, password))
    }

    suspend fun saveInfo(name: String, url: String, password: String) {
        val user = realmOnCloud.currentUser ?: return

        val info = PasswordInfo().apply {
            _id = RandomUUID().randomId
            this.url = url
            this.password = password
            this.name = name
            this._partitionKey = user.identity
        }

        val config = SyncConfiguration.Builder(user, user.identity, setOf(PasswordInfo::class))
            .build()
        val realm = Realm.open(config)

        realm.write {
            copyToRealm(info)
        }
    }

    fun getAllPassword(): CommonFlow<List<PasswordInfo>> {
        val user = realmOnCloud.currentUser!!
        val config = SyncConfiguration.Builder(user, user.identity, setOf(PasswordInfo::class))
            .build()
        val realm = Realm.open(config)
        return realm.query(PasswordInfo::class).asFlow().map {
            it.list
        }.asCommonFlow()
    }

}