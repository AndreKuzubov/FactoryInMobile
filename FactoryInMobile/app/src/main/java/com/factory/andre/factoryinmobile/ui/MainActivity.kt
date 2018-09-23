package com.factory.andre.factoryinmobile.ui

import android.os.Bundle
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpActivity
import com.factory.andre.factoryinmobile.R
import com.factory.andre.factoryinmobile.ui.view.EngineView

class MainActivity : MvpActivity() {

    @BindView(R.id.engine)
    lateinit var v: EngineView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)


    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}