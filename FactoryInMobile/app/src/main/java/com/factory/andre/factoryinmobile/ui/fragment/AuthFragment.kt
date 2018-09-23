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
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.factory.andre.factoryinmobile.FactoryApp
import com.factory.andre.factoryinmobile.R
import com.factory.andre.factoryinmobile.presenter.impl.AuthPresenter
import com.factory.andre.factoryinmobile.ui.LoginActivity
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
        val root = activity.layoutInflater.inflate(R.layout.fragment_auth, null)
        ButterKnife.bind(this, root)
        authPresenter.attachView(this)
        return root
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        FactoryApp.applicationComponent?.inject(this)
    }


    override fun authProgress() {
        Toast.makeText(activity, "authProgress", Toast.LENGTH_LONG).show()

        btAuth.isEnabled = false
        prAuthProgress.visibility = View.VISIBLE
    }

    override fun authErr(message: String?) {
        btAuth.isEnabled = true
        prAuthProgress.visibility = View.INVISIBLE
        Toast.makeText(activity, "authErr:$message", Toast.LENGTH_LONG).show()

    }

    override fun authSuccess() {
        Toast.makeText(activity, "authSuccess", Toast.LENGTH_LONG).show()
        (activity as LoginActivity).finishLogin()
    }


    @OnClick(R.id.bt_frautn_auth)
    fun auth() {
        authPresenter.auth(
                login = etLogin.text.toString(),
                password = etPassword.text.toString()
        )
    }

}