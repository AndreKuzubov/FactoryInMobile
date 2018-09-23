package com.factory.andre.factoryinmobile.realm.repository

import com.factory.andre.factoryinmobile.model.Auth
import com.factory.andre.factoryinmobile.realm.realmmodels.AuthRealm
import io.reactivex.Observable
import io.realm.Realm

interface IAuthRealmRepository {

    fun addAuth(auth: Auth)
    fun getAuth(): Observable<Auth>
}