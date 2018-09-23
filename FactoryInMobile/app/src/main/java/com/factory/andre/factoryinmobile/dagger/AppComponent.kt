package com.factory.andre.factoryinmobile.dagger

import android.app.Fragment
import com.factory.andre.factoryinmobile.FactoryApp
import com.factory.andre.factoryinmobile.dagger.modules.HostModule
import com.factory.andre.factoryinmobile.dagger.modules.PresenterModule
import com.factory.andre.factoryinmobile.dagger.modules.RealmModule
import com.factory.andre.factoryinmobile.ui.fragment.AuthFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    HostModule::class,
    RealmModule::class,
    PresenterModule::class
])
@Singleton
interface AppComponent {


    fun inject(fragment: AuthFragment)

}