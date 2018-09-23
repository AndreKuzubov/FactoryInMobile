package com.factory.andre.factoryinmobile.presenter.impl

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.factory.andre.factoryinmobile.presenter.IAuthPresenter
import com.factory.andre.factoryinmobile.realm.repository.IAuthRealmRepository
import com.factory.andre.factoryinmobile.retrofit.IAuthRestRepository
import com.factory.andre.factoryinmobile.ui.mvpinterfaces.IAuthView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
open class AuthPresenter
@Inject constructor(
        var authRealmRepository: IAuthRealmRepository,
        var authRestRepository: IAuthRestRepository
) : MvpPresenter<IAuthView>(), IAuthPresenter {


    override fun auth(login: String, password: String) {
        viewState.authProgress()
        authRestRepository.auth(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { it ->
                            viewState.authSuccess()
                        },
                        { err ->
                            viewState.authErr(err.message)
                        })
    }

}