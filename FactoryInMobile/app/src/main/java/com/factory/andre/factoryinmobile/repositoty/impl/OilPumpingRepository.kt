package com.factory.andre.factoryinmobile.repositoty.impl

import com.factory.andre.factoryinmobile.realm.db.IDBFactoryRealm
import com.factory.andre.factoryinmobile.repositoty.IOilPumpingRepository
import com.factory.andre.factoryinmobile.retrofit.IFactoryRest
import javax.inject.Inject

class OilPumpingRepository
@Inject
constructor(
        var factoryRealm: IDBFactoryRealm,
        var factoryRest: IFactoryRest
) : IOilPumpingRepository {
}