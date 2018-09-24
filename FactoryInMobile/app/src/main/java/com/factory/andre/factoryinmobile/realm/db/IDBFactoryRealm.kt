package com.factory.andre.factoryinmobile.realm.db

import com.factory.andre.factoryinmobile.model.Pump
import com.factory.andre.factoryinmobile.model.Tank
import com.factory.andre.factoryinmobile.model.base.IFactoryItemsModel
import io.reactivex.Observable

interface IDBFactoryRealm {

    fun <T : IFactoryItemsModel> get(factory: String, clazz: Class<T>): Observable<T>


    fun insertOrUpdate(tanks: List<Tank>?, pumps: List<Pump>?)

}