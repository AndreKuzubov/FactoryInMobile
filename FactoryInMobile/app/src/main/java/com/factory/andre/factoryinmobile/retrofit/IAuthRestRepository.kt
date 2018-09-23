package com.factory.andre.factoryinmobile.retrofit

import com.factory.andre.factoryinmobile.model.Auth
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

interface IAuthRestRepository {

    @POST("/auth")
    fun auth(@Query("l") login: String, @Query("p") passw: String): Observable<Auth>

}