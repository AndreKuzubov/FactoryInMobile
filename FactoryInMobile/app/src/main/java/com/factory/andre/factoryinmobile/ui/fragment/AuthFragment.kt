package com.factory.andre.factoryinmobile.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.OnTextChanged
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.factory.andre.factoryinmobile.FactoryApp
import com.factory.andre.factoryinmobile.R
import com.factory.andre.factoryinmobile.presenter.IAuthPresenter
import com.factory.andre.factoryinmobile.presenter.impl.AuthPresenter
import com.factory.andre.factoryinmobile.ui.mvpinterfaces.IAuthView
import javax.inject.Inject

class AuthFragment : MvpFragment(), IAuthView {

    @Inject
    @InjectPresenter
    lateinit var authPresenter: AuthPresenter

    @BindView(R.id.et_frauth_login)
    lateinit var etLogin: EditText

    @BindView(R.id.et_frauth_password)
    lateinit var etPassword: EditText

    @BindView(R.id.bt_frautn_auth)
    lateinit var btAuth: Button

    @BindView(R.id.pb_frauth_progress)
    lateinit var prAuthProgress: ProgressBar

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var root = activity.layoutInflater.inflate(R.layout.fragment_auth, null)
        ButterKnife.bind(this, root)

        return root
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        FactoryApp.applicationComponent?.inject(this)
    }

    override fun authProgress() {
        btAuth.isEnabled = false
        prAuthProgress.visibility = View.VISIBLE
    }

    override fun authErr(message: String?) {
        btAuth.isEnabled = true
        prAuthProgress.visibility = View.INVISIBLE
    }

    override fun authSuccess() {

    }

    @OnTextChanged(R.id.et_frauth_login)
    fun textChanged() {
    }

    @OnClick(R.id.bt_frautn_auth)
    open fun auth() {

        authPresenter.auth(
                login = etLogin.text.toString(),
                password = etPassword.text.toString()
        )
    }

}