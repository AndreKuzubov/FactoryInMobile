package com.factory.andre.factoryinmobile.dagger.modules

import com.factory.andre.factoryinmobile.realm.db.IDBAuthRealm
import com.factory.andre.factoryinmobile.realm.db.IDBFactoryRealm
import com.factory.andre.factoryinmobile.repositoty.IAuthRepository
import com.factory.andre.factoryinmobile.repositoty.IOilPumpingRepository
import com.factory.andre.factoryinmobile.repositoty.impl.AuthRepository
import com.factory.andre.factoryinmobile.repositoty.impl.OilPumpingRepository
import com.factory.andre.factoryinmobile.retrofit.IAuthRest
import com.factory.andre.factoryinmobile.retrofit.IFactoryRest
import dagger.Module
import dagger.Provides


@Module
class RepositoryModule() {


    @Provides
    fun provideAuthRepository(idbAuthRealm: IDBAuthRealm, iAuthRest: IAuthRest): IAuthRepository {
        return AuthRepository(idbAuthRealm, iAuthRest)
    }


    @Provides
    fun provideOilPumpingRepository(idbFactoryRealm: IDBFactoryRealm, iFactoryRest: IFactoryRest): IOilPumpingRepository {
        return OilPumpingRepository(idbFactoryRealm, iFactoryRest)
    }


}