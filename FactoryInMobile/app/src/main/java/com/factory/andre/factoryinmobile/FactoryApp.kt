package com.factory.andre.factoryinmobile

import com.arellomobile.mvp.MvpApplication
import com.factory.andre.factoryinmobile.dagger.AppComponent
import com.factory.andre.factoryinmobile.dagger.DaggerAppComponent
import com.factory.andre.factoryinmobile.dagger.modules.HostModule
import com.factory.andre.factoryinmobile.dagger.modules.PresenterModule
import com.factory.andre.factoryinmobile.dagger.modules.RealmModule


class FactoryApp() : MvpApplication() {

    companion object {
        var applicationComponent: AppComponent? = null
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerAppComponent.builder()
                .realmModule(RealmModule(this))
                .hostModule(HostModule())
                .presenterModule(PresenterModule())
                .build()

    }


}