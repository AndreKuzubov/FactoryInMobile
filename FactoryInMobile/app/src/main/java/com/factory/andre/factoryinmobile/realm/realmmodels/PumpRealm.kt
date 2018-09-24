package com.factory.andre.factoryinmobile.realm.realmmodels

import com.factory.andre.factoryinmobile.realm.realmmodels.base.IFactoryItemsRealm
import io.realm.RealmObject

open class PumpRealm constructor(
        override var id: Long? = null,

        override var factory: String? = null,
        var tag: String? = null,
//        TODO via enum
        var status: String? = null,

        var voltage: Float? = null,
        var allowVoltage: Float? = null,
        var turnovers: Float? = null,
        var allowTurnovers: Float? = null,
        var turnOn: Boolean? = null,
        override var timestamp: Long? = null

) : RealmObject(), IFactoryItemsRealm {


}