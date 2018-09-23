package com.factory.andre.factoryinmobile.realm.db.impl

import com.factory.andre.factoryinmobile.model.Auth
import com.factory.andre.factoryinmobile.realm.realmmodels.AuthRealm
import com.factory.andre.factoryinmobile.realm.db.IDBAuthRealm
import com.factory.andre.factoryinmobile.utils.generateId
import io.reactivex.*
import io.realm.Realm
import io.realm.RealmObject


open class DBAuthRealm() : IDBAuthRealm {


    override fun addAuth(auth: Auth) {
        Realm.getDefaultInstance().executeTransactionAsync { r ->
            r.delete(AuthRealm::class.java)
            val nextID = r.generateId(AuthRealm::class.java)
            var realmAuth = r.createObject(AuthRealm::class.java, nextID)
            realmAuth.login = auth.login
            realmAuth.name = auth.name
            realmAuth.password = auth.password
        }
    }

    override fun getAuth(): Observable<Auth> {
        return Observable.create(ObservableOnSubscribe<Auth> { e ->
            var realm = Realm.getDefaultInstance()
            var result = realm.where(AuthRealm::class.java).findFirst()

            var a: Auth? = null
            if (result != null) {
                a = Auth(
                        name = result.name,
                        login = result.login,
                        password = result.password)
            }

            e.onNext(a)
            e.onComplete()
        })

    }
}