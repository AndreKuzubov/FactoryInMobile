package com.factory.andre.factoryinmobile.dagger

import com.factory.andre.factoryinmobile.dagger.modules.HostModule
import com.factory.andre.factoryinmobile.dagger.modules.PresenterModule
import com.factory.andre.factoryinmobile.dagger.modules.RealmModule
import com.factory.andre.factoryinmobile.dagger.modules.RepositoryModule
import com.factory.andre.factoryinmobile.ui.fragment.AuthFragment
import com.factory.andre.factoryinmobile.ui.fragment.OilPumpingFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    HostModule::class,
    RealmModule::class,
    PresenterModule::class,
    RepositoryModule::class
])
@Singleton
interface AppComponent {

    fun inject(fragment: AuthFragment)

    fun inject(fragment: OilPumpingFragment)

}