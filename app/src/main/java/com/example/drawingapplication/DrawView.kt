package com.example.drawingapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import android.util.Log
import androidx.activity.viewModels
import com.example.drawingapplication.MainActivity

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


