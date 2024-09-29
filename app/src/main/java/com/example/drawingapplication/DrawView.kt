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
    val myViewModel = (context as MainActivity).myViewModel
    //width/height are 0 when the constructor is called
    //use the lazy delegated property to initialize it on first access, once the size is set
    private val rect: Rect by lazy {Rect(0,0,width, height)}

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(myViewModel.bm.value!!, 0f, 0f, null)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w < h) {
            myViewModel.changeScreenDimensions(w, h)
            Log.d("Screen", w.toString() + h.toString())
        }
        else {
            myViewModel.changeScreenDimensions(w, h)
            Log.d("Screen", w.toString() + h.toString())
        }
    }

}


