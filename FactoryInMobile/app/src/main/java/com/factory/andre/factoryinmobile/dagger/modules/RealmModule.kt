package com.factory.andre.factoryinmobile.dagger.modules

import android.content.Context
import com.factory.andre.factoryinmobile.realm.AppRealmModule
import com.factory.andre.factoryinmobile.realm.repository.IAuthRealmRepository
import com.factory.andre.factoryinmobile.realm.repository.impl.AuthRealmRepository
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration

@Module
class RealmModule(var context: Context) {

    init {
        Realm.init(this.context)
        val config = RealmConfiguration.Builder()
                .name("factory.realm")
                .schemaVersion(2)
                .modules(AppRealmModule())
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)
    }

    @Provides
    fun provideAuthRealm(): IAuthRealmRepository {
        return AuthRealmRepository()
    }

}