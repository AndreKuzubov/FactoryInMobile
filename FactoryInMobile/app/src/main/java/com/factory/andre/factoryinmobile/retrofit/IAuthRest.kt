package com.factory.andre.factoryinmobile.retrofit

import com.factory.andre.factoryinmobile.model.Auth
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

interface IAuthRest {

    @POST("/auth")
    fun auth(@Query("login") login: String, @Query("passw") passw: String): Observable<Auth>


}