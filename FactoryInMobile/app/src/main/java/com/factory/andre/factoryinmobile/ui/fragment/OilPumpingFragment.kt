package com.factory.andre.factoryinmobile.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.factory.andre.factoryinmobile.FactoryApp
import com.factory.andre.factoryinmobile.R
import com.factory.andre.factoryinmobile.presenter.impl.OilPumpingPresenter
import com.factory.andre.factoryinmobile.ui.mvpinterfaces.IOilPumpingView
import com.factory.andre.factoryinmobile.ui.view.FunctionalDiagramLayout
import com.factory.andre.factoryinmobile.ui.view.PumpView
import com.factory.andre.factoryinmobile.ui.view.TankView
import javax.inject.Inject

class OilPumpingFragment : MvpFragment(), IOilPumpingView {


    @Inject
    @InjectPresenter
    lateinit var oilPumpingPresenter: OilPumpingPresenter

    @BindView(R.id.fd_oilpumping)
    lateinit var funcDiagramLayout: FunctionalDiagramLayout

    @BindView(R.id.tv_oilp_tank1)
    lateinit var tankView1: TankView

    @BindView(R.id.pv_oilp_pump1)
    lateinit var pumpView1: PumpView

    @BindView(R.id.pv_oilp_pump2)
    lateinit var pumpView2: PumpView


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = activity.layoutInflater.inflate(R.layout.fragment_oilpumoing, null)
        ButterKnife.bind(this, root)
        oilPumpingPresenter.attachView(this)
        oilPumpingPresenter.init()

        funcDiagramLayout.addCommutationLine(R.id.pv_oilp_pump1, R.id.tv_oilp_tank1)
        funcDiagramLayout.addCommutationLine(R.id.pv_oilp_pump2, R.id.tv_oilp_tank1)
        return root
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        FactoryApp.applicationComponent?.inject(this)
    }


}