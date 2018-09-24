package com.factory.andre.factoryinmobile.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.factory.andre.factoryinmobile.utils.dpToPx

class TankView : View {

    enum class TankStatus {
        STABLE,
        WARNING,
        ALARM
    }

    var status: TankStatus = TankStatus.STABLE
        set (value) {
            field = value
            invalidate()
        }
        get

    var fillingPercent: Int = 0
        set(value) {
            if (value > 100) {
                field = 100
                invalidate()
                return
            }
            if (value < 0) {
                field = 0
                invalidate()
                return
            }
            field = value
            invalidate()
        }
        get

    private val strokePaint = Paint()
    private val fillPaint = Paint()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        strokePaint.style = Paint.Style.STROKE
        strokePaint.strokeWidth = dpToPx(2).toFloat()

        fillPaint.style = Paint.Style.FILL
        fillPaint.color = Color.BLUE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {

        canvas.drawColor(Color.WHITE)

        strokePaint.color = when (status) {
            TankStatus.STABLE -> Color.BLACK
            TankStatus.WARNING -> Color.YELLOW
            TankStatus.ALARM -> Color.RED
        }
        canvas.drawRect(0f, 0f,
                canvas.width.toFloat(), canvas.height.toFloat(),
                strokePaint)


        val fillLevel = canvas.height * fillingPercent / 100f
        canvas.drawRect(
                0f, canvas.height - fillLevel,
                canvas.width.toFloat(), canvas.height.toFloat(),
                fillPaint
        )


    }
}