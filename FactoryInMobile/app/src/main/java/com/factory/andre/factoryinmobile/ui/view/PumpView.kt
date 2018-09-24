package com.factory.andre.factoryinmobile.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.factory.andre.factoryinmobile.ui.view.PumpView.PumpStatus.*
import com.factory.andre.factoryinmobile.utils.dpToPx
import kotlin.math.min

class PumpView : View {

    enum class PumpStatus {
        ON,
        WARNING,
        ALARM,
        OFF,
    }

    var status: PumpStatus = OFF
        set(value) {
            field = value
            invalidate()
        }
        get

    var turnoversPercent: Int = 0
        set(value) {
            if (value < 0 || value > 100)
                return
            field = value
            invalidate()
        }
        get

    var voltagePercent: Int = 0
        set(value) {
            if (value < 0 || value > 100)
                return
            field = value
            invalidate()
        }
        get


    private val circlePaint = Paint()
    private val circleBackGroundPaint = Paint()
    private val arcPaint = Paint()


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        circleBackGroundPaint.style = Paint.Style.FILL_AND_STROKE
        circleBackGroundPaint.color = Color.WHITE

        circlePaint.style = Paint.Style.STROKE
        circlePaint.strokeWidth = dpToPx(3).toFloat()
        circlePaint.style = Paint.Style.FILL

        arcPaint.style = Paint.Style.FILL_AND_STROKE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)


    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        val cx = canvas.width / 2f
        val cy = canvas.height / 2f
        val radius = min(canvas.width, canvas.height) / 2f

        canvas.drawCircle(cx, cy, radius, circleBackGroundPaint)

        arcPaint.color = when (status) {
            ON -> Color.BLUE
            WARNING -> Color.YELLOW
            ALARM -> Color.RED
            OFF -> Color.DKGRAY
        }

        val arcRadius = radius * voltagePercent / 100f

        val sweepAgre = turnoversPercent * 360 / 100f
        canvas.drawArc(cx - arcRadius, cy - arcRadius, cx + arcRadius, cy + arcRadius,
                0f, sweepAgre, true, arcPaint)

        circlePaint.color = when (status) {
            ON -> Color.GREEN
            WARNING -> Color.YELLOW
            ALARM -> Color.RED
            OFF -> Color.DKGRAY
        }
        circlePaint.style = when (status) {
            OFF -> Paint.Style.FILL_AND_STROKE
            else -> Paint.Style.STROKE
        }
        canvas.drawCircle(cx, cy, radius, circlePaint)

    }

}
