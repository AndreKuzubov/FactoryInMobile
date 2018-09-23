package com.factory.andre.factoryinmobile.dagger.modules

import com.factory.andre.factoryinmobile.presenter.IAuthPresenter
import com.factory.andre.factoryinmobile.presenter.IOilPumpingPresenter
import com.factory.andre.factoryinmobile.presenter.impl.AuthPresenter
import com.factory.andre.factoryinmobile.presenter.impl.OilPumpingPresenter
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @Provides
    fun provideAuthPresenter(authPresenter: AuthPresenter): IAuthPresenter {
        return authPresenter
    }

    @Provides
    fun provideOilPumpingPresenter(oilPumpingPresenter: OilPumpingPresenter): IOilPumpingPresenter {
        return oilPumpingPresenter
    }
}