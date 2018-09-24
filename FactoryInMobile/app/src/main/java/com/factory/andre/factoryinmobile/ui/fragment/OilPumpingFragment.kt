package com.factory.andre.factoryinmobile.ui.fragment

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.BindView
import butterknife.BindViews
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.factory.andre.factoryinmobile.FactoryApp
import com.factory.andre.factoryinmobile.R
import com.factory.andre.factoryinmobile.model.Pump
import com.factory.andre.factoryinmobile.model.Tank
import com.factory.andre.factoryinmobile.presenter.impl.OilPumpingPresenter
import com.factory.andre.factoryinmobile.ui.mvpinterfaces.IOilPumpingView
import com.factory.andre.factoryinmobile.ui.view.FunctionalDiagramLayout
import com.factory.andre.factoryinmobile.ui.view.PumpView
import com.factory.andre.factoryinmobile.ui.view.TankView
import kotlinx.android.synthetic.main.fragment_oilpumoing.*
import javax.inject.Inject

class OilPumpingFragment : MvpFragment(), IOilPumpingView {


    val UPDATE_TICKER = 1000L

    @Inject
    @InjectPresenter
    lateinit var oilPumpingPresenter: OilPumpingPresenter


    @BindViews(R.id.pv_oilp_pumpportTankDiesel1_1portTankDiesel1,
            R.id.pv_oilp_portTankDiesel1tankerShipDiesel,
            R.id.pv_oilp_portTankGasoletankerShipGasole)
    lateinit var pumpViews: Array<PumpView>

    @BindViews(R.id.tv_oilp_portTankGasole,
            R.id.tv_oilp_porttankdiesel1,
            R.id.tv_oilp_porttankdiesel1_1,
            R.id.tv_oilp_tankerShipGasole,
            R.id.tv_oilp_tankershipdiesel)
    lateinit var tankViews: Array<TankView>


    @BindView(R.id.fd_oilpumping)
    lateinit var funcDiagramLayout: FunctionalDiagramLayout


    val countDownTimer = object : CountDownTimer(Long.MAX_VALUE, UPDATE_TICKER) {
        override fun onFinish() = Unit

        override fun onTick(millisUntilFinished: Long) {
            oilPumpingPresenter.init()
        }
    }


    override fun updatePumpState(pumps: List<Pump>) {

        for (pump in pumps) {

            val pv = pumpViews.find { pv -> pv.tag == pump.tag }
            pv?.status = if ((pump.voltage
                            ?: 0f) > 0) PumpView.PumpStatus.ON else PumpView.PumpStatus.OFF
            pv?.turnoversPercent = ((pump.turnovers ?: 0f) * 100f / (pump.allowTurnovers
                    ?: 0f)).toInt()
            pv?.voltagePercent = ((pump.voltage ?: 0f) * 100f / (pump.allowVoltage
                    ?: 0f)).toInt()
        }
    }

    override fun updateTankState(tanks: List<Tank>) {
        for (tank in tanks) {
            val tv = tankViews.find { tv -> tv.tag == tank.tag }
            tv?.fillingPercent = ((tank.fill ?: 0f) * 100f / (tank.volume
                    ?: 0f)).toInt()
        }

    }

    override fun onError(mes: String) {
//        Toast.makeText(activity, "onError: $mes", Toast.LENGTH_SHORT).show()
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = activity.layoutInflater.inflate(R.layout.fragment_oilpumoing, null)
        ButterKnife.bind(this, root)
        oilPumpingPresenter.attachView(this)
        oilPumpingPresenter.init()
        countDownTimer.start()

        funcDiagramLayout.addCommutationLine(R.id.tv_oilp_porttankdiesel1_1, R.id.pv_oilp_pumpportTankDiesel1_1portTankDiesel1)
        funcDiagramLayout.addCommutationLine(R.id.pv_oilp_pumpportTankDiesel1_1portTankDiesel1, R.id.tv_oilp_porttankdiesel1)
        funcDiagramLayout.addCommutationLine(R.id.tv_oilp_porttankdiesel1, R.id.pv_oilp_portTankDiesel1tankerShipDiesel)
        funcDiagramLayout.addCommutationLine(R.id.pv_oilp_portTankDiesel1tankerShipDiesel, R.id.tv_oilp_tankershipdiesel)

        funcDiagramLayout.addCommutationLine(R.id.tv_oilp_portTankGasole, R.id.pv_oilp_portTankGasoletankerShipGasole)
        funcDiagramLayout.addCommutationLine(R.id.pv_oilp_portTankGasoletankerShipGasole, R.id.tv_oilp_tankerShipGasole)

        return root
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        FactoryApp.applicationComponent?.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }


}