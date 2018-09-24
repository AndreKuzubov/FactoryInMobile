package com.factory.andre.factoryinmobile.repositoty.impl

import com.factory.andre.factoryinmobile.model.Pump
import com.factory.andre.factoryinmobile.model.Tank
import com.factory.andre.factoryinmobile.model.base.IBaseModel
import com.factory.andre.factoryinmobile.model.base.IFactoryItemsModel
import com.factory.andre.factoryinmobile.realm.db.IDBFactoryRealm
import com.factory.andre.factoryinmobile.repositoty.IOilPumpingRepository
import com.factory.andre.factoryinmobile.retrofit.IFactoryRest
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class OilPumpingRepository
@Inject
constructor(
        var factoryRealm: IDBFactoryRealm,
        var factoryRest: IFactoryRest
) : IOilPumpingRepository {

    val MAX_DELAY = 1000

    override fun getFactoryTanks(factoryname: String): Observable<Tank> {
        return getFactoryItems<Tank>(factoryname = factoryname)
    }

    override fun getFactoryPumps(factoryname: String): Observable<Pump> {
        return getFactoryItems<Pump>(factoryname = factoryname)
    }

    private inline fun <reified T : IFactoryItemsModel> getFactoryItems(factoryname: String): Observable<T> {
        val curTimestapp = System.currentTimeMillis()
        val items = ArrayList<T>()
        val outObservable = PublishSubject.create<T>()
        val ignore = factoryRealm.get(factory = factoryname, clazz = T::class.java)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    items.add(it)
                },
                        { err ->
                            outObservable.onError(err)
                        },
                        {
                            if (items.size == 0) {
                                getViaRest(
                                        factoryname = factoryname,
                                        publishSubject = outObservable)
                                return@subscribe
                            }

                            for (t in items)
                                if (curTimestapp - (t?.timestamp ?: 0) > MAX_DELAY) {
                                    getViaRest(
                                            factoryname = factoryname,
                                            publishSubject = outObservable)
                                    break
                                }

                            if (!outObservable.hasComplete())
                                for (p in items)
                                    outObservable.onNext(p)
                            outObservable.onComplete()
                        }
                )

        return outObservable
                .observeOn(AndroidSchedulers.mainThread())
    }

    private inline fun <reified T : IBaseModel> getViaRest(factoryname: String, publishSubject: PublishSubject<T>) {
        factoryRest.getFactoryDiagram(name = factoryname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { it ->
                            factoryRealm.insertOrUpdate(
                                    tanks = it.tanks,
                                    pumps = it.pumpes)
                            if (T::class.java == Pump::class.java)
                                for (p in it.pumpes!!) {
                                    p.factory = factoryname
                                    p.timestamp = System.currentTimeMillis()
                                    publishSubject.onNext(p as T)
                                }
                            else
                                if (T::class.java == Tank::class.java)
                                    for (t in it.tanks!!) {
                                        t.factory = factoryname
                                        t.timestamp = System.currentTimeMillis()
                                        publishSubject.onNext(t as T)
                                    }
                            publishSubject.onComplete()
                        }, { err ->
                }
                )
    }

}