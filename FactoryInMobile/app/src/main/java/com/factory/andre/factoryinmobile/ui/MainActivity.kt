package com.factory.andre.factoryinmobile.ui

import android.os.Bundle
import android.widget.FrameLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpActivity
import com.factory.andre.factoryinmobile.R
import com.factory.andre.factoryinmobile.ui.fragment.OilPumpingFragment

class MainActivity : MvpActivity() {

    @BindView(R.id.fl_fragment_container)
    lateinit var flFragmentContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        if (fragmentManager.findFragmentById(R.id.fl_fragment_container) == null) {
            val fragment = OilPumpingFragment()
            fragmentManager.beginTransaction()
                    .add(R.id.fl_fragment_container, fragment)
                    .commit()

        }

    }

}