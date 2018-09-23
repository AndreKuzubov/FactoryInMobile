package com.factory.andre.factoryinmobile.realm.db

import com.factory.andre.factoryinmobile.model.Auth
import io.reactivex.Observable

interface IDBAuthRealm {

    fun addAuth(auth: Auth)
    fun getAuth(): Observable<Auth>
}