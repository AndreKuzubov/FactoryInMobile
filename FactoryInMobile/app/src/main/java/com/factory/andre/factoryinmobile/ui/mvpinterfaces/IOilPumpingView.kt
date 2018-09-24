package com.factory.andre.factoryinmobile.ui.mvpinterfaces

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.factory.andre.factoryinmobile.model.Pump
import com.factory.andre.factoryinmobile.model.Tank

@StateStrategyType(AddToEndSingleStrategy::class)
interface IOilPumpingView : MvpView {

    fun updatePumpState(pumps: List<Pump>)

    fun updateTankState(tanks: List<Tank>)

    fun onError(mes: String)

}