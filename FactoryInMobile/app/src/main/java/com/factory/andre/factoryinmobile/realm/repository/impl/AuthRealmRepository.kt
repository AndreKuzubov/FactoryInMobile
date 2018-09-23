package com.factory.andre.factoryinmobile.realm.repository.impl

import com.factory.andre.factoryinmobile.model.Auth
import com.factory.andre.factoryinmobile.realm.realmmodels.AuthRealm
import com.factory.andre.factoryinmobile.realm.repository.IAuthRealmRepository
import io.reactivex.*
import io.realm.Realm

open class AuthRealmRepository() : IAuthRealmRepository {

    override fun addAuth(auth: Auth) {
        var realm = Realm.getDefaultInstance()
        realm.executeTransactionAsync { r ->
            r.delete(AuthRealm::class.java)

            var realmAuth = r.createObject(AuthRealm::class.java)
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