package com.factory.andre.factoryinmobile.realm.realmmodels.base

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

interface IModelRealm {
    var id: Long?
    var timestamp: Long?
}