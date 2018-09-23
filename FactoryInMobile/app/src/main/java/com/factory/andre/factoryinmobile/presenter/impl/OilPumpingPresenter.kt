package com.factory.andre.factoryinmobile.presenter.impl

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.factory.andre.factoryinmobile.presenter.IOilPumpingPresenter
import com.factory.andre.factoryinmobile.repositoty.IOilPumpingRepository
import com.factory.andre.factoryinmobile.ui.mvpinterfaces.IOilPumpingView
import javax.inject.Inject

@InjectViewState
open class OilPumpingPresenter
@Inject constructor(
        var pumpingRepository: IOilPumpingRepository
) : MvpPresenter<IOilPumpingView>(), IOilPumpingPresenter {


    override fun init() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun turnPump(turnOn: Boolean) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}