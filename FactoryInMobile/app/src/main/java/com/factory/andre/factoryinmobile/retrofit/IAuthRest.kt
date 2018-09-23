package com.factory.andre.factoryinmobile.retrofit

import com.factory.andre.factoryinmobile.model.Auth
import io.reactivex.Observable
import retrofit2.http.*

interface IAuthRest {

    @POST("/auth")
    fun auth(@Query("login") login: String, @Query("passw") passw: String): Observable<Auth>


}