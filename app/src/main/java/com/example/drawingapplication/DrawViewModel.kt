package com.example.drawingapplication

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DrawViewModel : ViewModel() {
    val bitmap:MutableLiveData<Bitmap> = MutableLiveData<Bitmap>(Bitmap.createBitmap(1200, 2400, Bitmap.Config.ARGB_8888))
    private val rect: Rect by lazy {Rect(0,0, 600, 1000)}
    val bitmapCanvas = Canvas(bitmap.value!!)

    var startX = 50f
    var startY = 50f
    var endX = 50f
    var endY  = 50f
    var paint = Paint()

    val bm = bitmap as LiveData<Bitmap>


    @RequiresApi(Build.VERSION_CODES.O)
    fun draw() {
        val currentBitmap = bitmap.value!!
        val canvas = Canvas(currentBitmap)

        paint.color = Color.BLACK
        paint.strokeWidth = 8f

        canvas.drawLine(startX, startY, endX, endY, paint)

        // Notify observers about the updated bitmap
        bitmap.value = currentBitmap
    }
}