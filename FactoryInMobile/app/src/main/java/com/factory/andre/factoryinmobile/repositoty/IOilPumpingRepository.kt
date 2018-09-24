package com.factory.andre.factoryinmobile.repositoty

import com.factory.andre.factoryinmobile.model.Pump
import com.factory.andre.factoryinmobile.model.Tank
import io.reactivex.Observable

interface IOilPumpingRepository {

    fun getFactoryPumps(factoryname: String): Observable<Pump>

    fun getFactoryTanks(factoryname: String): Observable<Tank>


}