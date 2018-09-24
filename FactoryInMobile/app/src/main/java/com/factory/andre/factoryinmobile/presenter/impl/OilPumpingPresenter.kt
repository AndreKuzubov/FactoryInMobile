package com.factory.andre.factoryinmobile.presenter.impl

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.factory.andre.factoryinmobile.model.Pump
import com.factory.andre.factoryinmobile.model.Tank
import com.factory.andre.factoryinmobile.presenter.IOilPumpingPresenter
import com.factory.andre.factoryinmobile.repositoty.IOilPumpingRepository
import com.factory.andre.factoryinmobile.ui.mvpinterfaces.IOilPumpingView
import javax.inject.Inject

@InjectViewState
open class OilPumpingPresenter
@Inject constructor(
        var pumpingRepository: IOilPumpingRepository
) : MvpPresenter<IOilPumpingView>(), IOilPumpingPresenter {

    val FACTORY_NAME = "oilPumping"

    override fun init() {

        val pumps = ArrayList<Pump>()
        pumpingRepository.getFactoryPumps(factoryname = FACTORY_NAME)
                .subscribe(
                        { it ->
                            pumps.add(it)
                        },
                        { err ->
                            viewState.onError(mes = err.message.toString())
                        },
                        {
                            viewState.updatePumpState(pumps = pumps)
                        }
                )

        val tanks = ArrayList<Tank>()

        pumpingRepository.getFactoryTanks(factoryname = FACTORY_NAME)
                .subscribe(
                        { it ->
                            tanks.add(it)
                        },
                        { err ->
                            viewState.onError(mes = err.message.toString())
                        },
                        {
                            viewState.updateTankState(tanks = tanks)
                        }
                )
    }

    override fun turnPump(turnOn: Boolean) {
        TODO("turnPump not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}