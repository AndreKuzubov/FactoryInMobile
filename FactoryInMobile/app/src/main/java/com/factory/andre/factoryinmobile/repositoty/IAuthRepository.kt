package com.factory.andre.factoryinmobile.repositoty

import com.factory.andre.factoryinmobile.model.Auth
import io.reactivex.Observable

interface IAuthRepository {

    fun auth(login: String, password: String): Observable<Auth>
}