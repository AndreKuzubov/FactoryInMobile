package com.factory.andre.factoryinmobile.realm

import com.factory.andre.factoryinmobile.realm.realmmodels.AuthRealm
import com.factory.andre.factoryinmobile.realm.realmmodels.PumpRealm
import com.factory.andre.factoryinmobile.realm.realmmodels.TankRealm
import io.realm.annotations.RealmModule

@RealmModule(classes = [AuthRealm::class, PumpRealm::class, TankRealm::class])
public open class AppRealmModule() {

}