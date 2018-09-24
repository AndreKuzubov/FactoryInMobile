package com.factory.andre.factoryinmobile.realm.realmmodels

import com.factory.andre.factoryinmobile.realm.realmmodels.base.IFactoryItemsRealm
import io.realm.RealmObject

open class TankRealm constructor(
        override var id: Long? = null,

        override var factory: String? = null,
        var tag: String? = null,
        var volume: Float? = null,
        var status: String? = null,
        var fill: Float? = null,

        override var timestamp: Long? = null

) : RealmObject(), IFactoryItemsRealm {

}
