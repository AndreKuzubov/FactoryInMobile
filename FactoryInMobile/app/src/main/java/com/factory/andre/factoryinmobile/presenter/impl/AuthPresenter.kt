package com.factory.andre.factoryinmobile.presenter.impl

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.factory.andre.factoryinmobile.presenter.IAuthPresenter
import com.factory.andre.factoryinmobile.repositoty.impl.AuthRepository
import com.factory.andre.factoryinmobile.ui.mvpinterfaces.IAuthView
import javax.inject.Inject

@InjectViewState
open class AuthPresenter
@Inject constructor(
        var authRepository: AuthRepository
) : MvpPresenter<IAuthView>(), IAuthPresenter {


    override fun auth(login: String, password: String) {
        viewState.authProgress()
        authRepository.auth(login, password).subscribe(
                { it ->
                    viewState.authSuccess()
                },
                { err ->
                    viewState.authErr(err.message)
                })

    }

}