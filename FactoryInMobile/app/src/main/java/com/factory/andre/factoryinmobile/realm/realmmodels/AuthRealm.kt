package com.factory.andre.factoryinmobile.realm.realmmodels

import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class AuthRealm constructor(
        @PrimaryKey
        var id: Int?,
        var name: String?,
        var login: String?,
        var password: String?,
        var permissions: String?
) : RealmObject() {


    constructor() : this(null, null, null, null, null) {

    }


}