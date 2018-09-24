package com.factory.andre.factoryinmobile.retrofit

import com.factory.andre.factoryinmobile.model.Factory
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Path

interface IFactoryRest {

    @POST("/factory{name}")
    fun getFactoryDiagram(@Path("name") name: String): Observable<Factory>


}