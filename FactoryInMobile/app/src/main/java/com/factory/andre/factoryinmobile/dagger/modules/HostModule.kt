package com.factory.andre.factoryinmobile.dagger.modules

import com.factory.andre.factoryinmobile.retrofit.IAuthRestRepository
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class HostModule() {


    @Provides
    fun provideAuthRestofit(): IAuthRestRepository {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.0.101:5050")
                .build().create(IAuthRestRepository::class.java);
    }


}