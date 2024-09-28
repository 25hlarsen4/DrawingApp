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

class DrawView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    //width/height are 0 when the constructor is called
    //use the lazy delegated property to initialize it on first access, once the size is set
    private val rect: Rect by lazy {Rect(0,0,width, height)}

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        (context as MainActivity).myViewModel.bm.value.let {
            canvas.drawBitmap(it!!, 0f, 0f, null)
        }
    }

}


