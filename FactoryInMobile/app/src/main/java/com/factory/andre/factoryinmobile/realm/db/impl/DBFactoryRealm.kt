package com.factory.andre.factoryinmobile.realm.db.impl

import com.factory.andre.factoryinmobile.model.Pump
import com.factory.andre.factoryinmobile.model.Tank
import com.factory.andre.factoryinmobile.model.base.IFactoryItemsModel
import com.factory.andre.factoryinmobile.realm.db.IDBFactoryRealm
import com.factory.andre.factoryinmobile.realm.realmmodels.PumpRealm
import com.factory.andre.factoryinmobile.realm.realmmodels.TankRealm
import com.factory.andre.factoryinmobile.utils.forEachConvert
import com.factory.andre.factoryinmobile.utils.generateId
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.realm.Realm
import io.realm.RealmObject

class DBFactoryRealm() : IDBFactoryRealm {

    override fun <T : IFactoryItemsModel> get(factory: String, clazz: Class<T>): Observable<T> {


        return Observable.create(ObservableOnSubscribe<T> { e ->
            val realm = Realm.getDefaultInstance()
            val realmClazz: Class<out RealmObject> = when (clazz) {
                Pump::class.java -> PumpRealm::class.java
                Tank::class.java -> TankRealm::class.java
                else -> TODO("DBFactoryRealm not implemented type")
            }
            val results = realm.where(realmClazz).equalTo(IFactoryItemsModel::factory.name, factory).findAll()
            for (r in results) when (r) {
                is PumpRealm -> {
                    val pump = Pump(
                            factory = r.factory,
                            tag = r.tag,
                            status = r.status,
                            turnOn = r.turnOn,
                            allowTurnovers = r.allowTurnovers,
                            allowVoltage = r.allowVoltage,
                            turnovers = r.turnovers,
                            voltage = r.voltage,
                            timestamp = r.timestamp
                    )
                    e.onNext(pump as T)
                }
                is TankRealm -> {
                    val tank = Tank(
                            factory = r.factory,
                            tag = r.tag,
                            status = r.status,
                            volume = r.volume,
                            fill = r.fill,
                            timestamp = r.timestamp
                    )
                    e.onNext(tank as T)
                }
                else -> TODO("DBFactoryRealm not implemented type")
            }

            e.onComplete()
        })

    }

    override fun insertOrUpdate(tanks: List<Tank>?, pumps: List<Pump>?) {
        Realm.getDefaultInstance().executeTransactionAsync() { realm ->
            if (pumps != null) {
                val updatePumpsRealm = realm.where(PumpRealm::class.java)
                        .`in`(PumpRealm::tag.name,
                                pumps.forEachConvert { it ->
                                    it.tag
                                }.toTypedArray()
                        )
                        .findAll()

                for (up in updatePumpsRealm) {
                    val sourcePump = pumps.find { p -> p.tag == up.tag }
                    up.status = sourcePump?.status
                    up.voltage = sourcePump?.voltage
                    up.turnovers = sourcePump?.turnovers
                    up.turnOn = sourcePump?.turnOn
                    up.timestamp = sourcePump?.timestamp
                }

                val insertPumps = pumps.filter { p ->
                    updatePumpsRealm.find { up -> up.tag == p.tag } == null
                }


                for (p in insertPumps) {
                    realm.insert(
                            PumpRealm(
                                    id = realm.generateId(PumpRealm::class.java),
                                    factory = p.factory,
                                    tag = p.tag,
                                    allowVoltage = p.allowVoltage,
                                    allowTurnovers = p.allowTurnovers,
                                    status = p.status,
                                    voltage = p.voltage,
                                    turnovers = p.turnovers,
                                    turnOn = p.turnOn,
                                    timestamp = p.timestamp
                            )
                    )
                }

            }

            if (tanks != null) {
                val updateTankRealm = realm.where(TankRealm::class.java)
                        .`in`(TankRealm::tag.name,
                                tanks.forEachConvert { it ->
                                    it.tag
                                }.toTypedArray()
                        )
                        .findAll()

                for (ut in updateTankRealm) {

                    val sourceTank = tanks.find { t -> t.tag == ut.tag }
                    ut.status = sourceTank?.status
                    ut.fill = sourceTank?.fill
                    ut.timestamp = sourceTank?.timestamp
                }

                val insertTank = tanks.filter { t ->
                    updateTankRealm.find { ut -> ut.tag == t.tag } == null
                }


                for (p in insertTank) {
                    realm.insert(
                            TankRealm(

                                    id = realm.generateId(TankRealm::class.java),
                                    factory = p.factory,
                                    tag = p.tag,
                                    status = p.status,
                                    fill = p.fill,
                                    volume = p.volume,
                                    timestamp = p.timestamp
                            )
                    )
                }

            }
        }
    }


}