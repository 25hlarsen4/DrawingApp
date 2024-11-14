package com.example.drawingapplication

import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi

class DrawView(context: Context, attrs: AttributeSet) : View(context, attrs) {

//    // Get MainActivity's viewmodel from context
//    val myViewModel = (context as MainActivity).myViewModel
//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//        // When called update the canvas bitmap
//        canvas.drawBitmap(myViewModel.bm.value!!, 0f, 0f, null)
//    }
//
//    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
//        super.onSizeChanged(w, h, oldw, oldh)
//        // If the canvas size is changed then a screen rotation has happened
//        myViewModel.changeScreenDimensions(w, h)
//
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        // If event is null return instantly
//        event ?: return false
//
//        // If event isn't null then see what action the user did
//        when (event.action) {
//            // If the user presses click then record initial position
//            MotionEvent.ACTION_DOWN -> {
//                myViewModel.startX = event.x
//                myViewModel.startY = event.y
//            }
//            // If the user moves the mouse while clicked then record it as the end position then draw.
//            // After drawing record position in case user moves cursor again.
//            MotionEvent.ACTION_MOVE -> {
//                myViewModel.endX = event.x
//                myViewModel.endY = event.y
//
//                myViewModel.draw()
//
//                myViewModel.startX = event.x
//                myViewModel.startY = event.y
//            }
//            // If the user lets go of click then record position then draw.
//            MotionEvent.ACTION_UP -> {
//
//                myViewModel.endX = event.x
//                myViewModel.endY = event.y
//                myViewModel.draw()
//            }
//        }
//        return true
//    }
}


