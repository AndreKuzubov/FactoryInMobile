package com.factory.andre.factoryinmobile.ui.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class EngineView(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    open override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    open override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }


}