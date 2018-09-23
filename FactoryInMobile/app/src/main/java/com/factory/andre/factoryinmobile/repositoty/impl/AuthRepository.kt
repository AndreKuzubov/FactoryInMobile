package com.factory.andre.factoryinmobile.repositoty.impl

import com.factory.andre.factoryinmobile.model.Auth
import com.factory.andre.factoryinmobile.realm.db.IDBAuthRealm
import com.factory.andre.factoryinmobile.repositoty.IAuthRepository
import com.factory.andre.factoryinmobile.retrofit.IAuthRest
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthRepository @Inject constructor(
        var authRealm: IDBAuthRealm,
        var authRest: IAuthRest
) : IAuthRepository {

    override fun auth(login: String, password: String): Observable<Auth> {
        var observable = authRest.auth(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        observable
                .subscribe(
                        { it ->
                            authRealm.addAuth(it)
                        },
                        { err ->

                        })
        return observable
    }
}