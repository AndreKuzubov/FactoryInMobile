package com.factory.andre.factoryinmobile.dagger.modules

import com.factory.andre.factoryinmobile.retrofit.IAuthRest
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class HostModule() {


    @Provides
    fun provideAuthRestofit(): IAuthRest {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.100:5050")
                .build()
                .create(IAuthRest::class.java);
    }


}