package com.factory.andre.factoryinmobile.ui.mvpinterfaces

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IAuthView : MvpView {


    // ожидание авторизации
    fun authProgress()

    // ошибка при авторизации
    @StateStrategyType(SingleStateStrategy::class)
    fun authErr(message: String?)

    fun authSuccess()

}