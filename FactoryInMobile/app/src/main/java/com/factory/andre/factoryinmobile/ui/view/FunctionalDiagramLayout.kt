package com.factory.andre.factoryinmobile.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import com.factory.andre.factoryinmobile.utils.dpToPx
import java.util.*


//TODO make xml attributes CommutationLine
class FunctionalDiagramLayout : ConstraintLayout {

    enum class Direction {
        DIRECTLY,
        REVERSE,
        NO_DIRECTION
    }

    val paint = Paint()

    @SuppressLint("UseSparseArrays")
    var lines = HashMap<Pair<Int, Int>, Direction>()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        setWillNotDraw(false)
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.color = Color.BLACK
        paint.strokeWidth = dpToPx(2).toFloat()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        invalidate()
    }

    fun addCommutationLine(idFrom: Int, idTo: Int, direction: Direction = Direction.DIRECTLY) {
        lines.put((idFrom to idTo), direction)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {


        for ((k, v) in lines.entries) {
            val child1: View = findViewById(k.first)
            val child2: View = findViewById(k.second)

            val stX = child1.x + child1.width / 2f
            val stY = child1.y + child1.height / 2f

            val enX = child2.x + child2.width / 2f
            val enY = child2.y + child2.height / 2f

            canvas.drawLine(stX, stY, enX, enY, paint)

        }

    }

}

