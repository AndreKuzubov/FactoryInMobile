package com.factory.andre.factoryinmobile.ui

import android.os.Bundle
import android.widget.FrameLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpActivity
import com.factory.andre.factoryinmobile.R
import com.factory.andre.factoryinmobile.ui.fragment.AuthFragment


class LoginActivity : MvpActivity() {


    @BindView(R.id.fl_auth_container)
    lateinit var authContainer: FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this);


        if (fragmentManager.findFragmentById(R.id.fl_auth_container) == null) {
            var fragment = AuthFragment()
            fragmentManager.beginTransaction()
                    .add(R.id.fl_auth_container, fragment)
                    .commit()

        }
    }

    override fun onStart() {
        super.onStart()
    }

}
