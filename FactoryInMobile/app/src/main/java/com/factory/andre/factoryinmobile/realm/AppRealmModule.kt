package com.factory.andre.factoryinmobile.realm

import com.factory.andre.factoryinmobile.model.Auth
import com.factory.andre.factoryinmobile.realm.realmmodels.AuthRealm
import io.realm.annotations.RealmModule

@RealmModule(classes = [AuthRealm::class])
public open class AppRealmModule() {

}