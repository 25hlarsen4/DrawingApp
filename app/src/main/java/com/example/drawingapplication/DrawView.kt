package com.example.drawingapplication

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class DrawView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    // Get MainActivity's viewmodel from context
    val myViewModel = (context as MainActivity).myViewModel

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // When called update the canvas bitmap
        canvas.drawBitmap(myViewModel.bm.value!!, 0f, 0f, null)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // If the canvas size is changed then a screen rotation has happened
        myViewModel.changeScreenDimensions(w, h)

    }

}


