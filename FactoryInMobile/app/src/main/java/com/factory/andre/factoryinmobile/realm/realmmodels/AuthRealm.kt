package com.factory.andre.factoryinmobile.realm.realmmodels

import com.factory.andre.factoryinmobile.realm.realmmodels.base.IModelRealm
import dagger.Provides
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class AuthRealm constructor(
        @PrimaryKey
        override var id: Long? = null,
        var name: String? = null,
        var login: String? = null,
        var password: String? = null,
        var permissions: String? = null,
        override var timestamp: Long? = null
) : RealmObject(), IModelRealm {


}